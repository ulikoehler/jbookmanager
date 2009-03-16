/*
 * DOM may be slow when used on large XML documents and may consume incredibly much memory!
 * SAX implementation follows
 */
package jbookmanager.model;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author uli
 */
public class LibraryDOMReader
{

    private Logger logger = Logger.getLogger(LibraryDOMReader.class.getName());

    private Library parseXMLLibrary(String filename)
    {
        try
        {
            Library ret = new Library();
            /**
             * Build a DOM tree of the document
             */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("employees.xml");
            /**
             * Gather the data
             */
            Element root = doc.getDocumentElement();
            //Parse the books
            NodeList bookList = root.getElementsByTagName("book");
            for(int i = 0; i < bookList.getLength(); i++)
            {
                ret.addBook(getBook(bookList.item(i)));
            }
            //TODO implement order parsing
            NodeList orderList = root.getElementsByTagName("order");
        }
        catch (SAXException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        catch (IOException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        catch (ParserConfigurationException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }

    }

    private static Book getBook(Node bookElement)
    {
        Book ret = new Book(); //Returned at the end
        ret.setIsbn(bookElement.getAttribute("isbn"));
        NodeList childNodes = bookElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Node node = childNodes.item(i);
            if (node.getNodeName().equals("title"))
            {
                ret.setTitle(node.getNodeValue());
            }
            if (node.getNodeName().equals("price"))
            {
                ret.setPrice(Double.parseDouble(node.getNodeValue()));
            }
            if (node.getNodeName().equals("count"))
            {
                ret.setCount(Integer.parseInt(node.getNodeValue()));
            }
            if (node.getNodeName().equals("comment"))
            {
                ret.setComment(node.getNodeValue());
            }
        }
        return ret;
    }
}
