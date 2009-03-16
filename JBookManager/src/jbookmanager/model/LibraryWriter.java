/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author uli
 */
public class LibraryWriter
{
    public static void writeCompressedLibrary(Library library, String file) throws IOException
    {
        writeLibraryInternal(library,  new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file))));
    }

    public static void writeLibraryPlain(Library library, String filename) throws IOException
    {
        writeLibraryInternal(library, new BufferedOutputStream(new FileOutputStream(filename)));
    }

    private static void writeLibraryInternal(Library library, OutputStream fout)
    {
        // PrintWriter from a Servlet
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(fout);//;
            StreamResult streamResult = new StreamResult(out);
            SAXTransformerFactory tf =
                    (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            //SAX 2.0 ContentHandler
            TransformerHandler th = tf.newTransformerHandler();
            Transformer serializer = th.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            th.setResult(streamResult);
            th.startDocument();
            AttributesImpl emptyAtts = new AttributesImpl();
            AttributesImpl atts = new AttributesImpl();
            th.startElement("", "", "library", emptyAtts); //Root element with schema declarations
            //Write all books
            th.startElement("","","books", emptyAtts);
            for (Book b : library.getBooks())
            {
                atts.clear();
                atts.addAttribute("", "", "isbn", "CDATA", b.getIsbn());
                th.startElement("", "", "book", atts);
                    //Title
                    th.startElement("", "", "title", emptyAtts);
                        char[] title = b.getTitle().toCharArray();
                        th.characters(title, 0, title.length);
                    th.endElement("", "", "title");

                    th.startElement("", "", "price", emptyAtts);
                        char[] price = Double.toString(b.getPrice()).toCharArray();
                        th.characters(price, 0, price.length);
                    th.endElement("", "", "price");

                    th.startElement("", "", "count", emptyAtts);
                        char[] count = Integer.toString(b.getCount()).toCharArray();
                        th.characters(count, 0, count.length);
                    th.endElement("", "", "count");

                    th.startElement("", "", "comment", emptyAtts);
                        char[] comment = b.getComment().toCharArray();
                        th.characters(comment, 0, comment.length);
                    th.endElement("", "", "comment");
                
                th.endElement("", "", "book");
            }
            th.endElement("", "", "books");
            th.endElement("", "", "library");
            th.endDocument();
        }
        catch (SAXException ex)
        {
            Logger.getLogger(LibraryWriter.class.getName()).log(Level.ERROR, null, ex);
        }
        catch (TransformerConfigurationException ex)
        {
            Logger.getLogger(LibraryWriter.class.getName()).log(Level.ERROR, null, ex);
        }
        finally
        {
            out.close();
        }
    }
}
