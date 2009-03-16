/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static void writeLibrary(Library library, String file)
    {
        // PrintWriter from a Servlet
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(new FileOutputStream(file));//new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file))));
            StreamResult streamResult = new StreamResult(out);
            SAXTransformerFactory tf =
                    (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            // SAX2.0 ContentHandler.
            TransformerHandler th = tf.newTransformerHandler();
            Transformer serializer = th.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            th.setResult(streamResult);
            th.startDocument();
            AttributesImpl emptyAtts = new AttributesImpl();
            AttributesImpl atts = new AttributesImpl();
            //Write the XSD stylesheet reference
            atts.addAttribute("", "", "xmlns:xsi", "CDATA", "http://www.w3.org/2001/XMLSchema");
            atts.addAttribute("", "", "xsi:noNamespaceSchemaLocation", "CDATA", "Libary.xsd");

            th.startElement("", "", "confguration", atts);
            th.endElement("", "", "confguration");
            
            th.startElement("", "", "library", emptyAtts);
            //Write all books
            th.startElement("","","books", emptyAtts);
            for (Book b : library.getBooks())
            {
                System.out.println(b.getTitle());
                atts.clear();
                atts.addAttribute("", "", "isbn", "CDATA", b.getIsbn());
                th.startElement("", "", "book", atts);
                    //Title
                    th.startElement("", "", "titlee", emptyAtts);
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
        catch (IOException ex)
        {
            Logger.getLogger(LibraryWriter.class.getName()).log(Level.ERROR, null, ex);
        }
        finally
        {
            out.close();
        }

    }
}
