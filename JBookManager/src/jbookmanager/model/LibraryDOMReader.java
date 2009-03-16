/*
 * DOM may be slow when used on large XML documents and may consume incredibly much memory!
 * SAX implementation follows
 */
package jbookmanager.model;

import java.io.IOException;
import java.io.InputStream;
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

    private static Logger logger = Logger.getLogger(LibraryDOMReader.class.getName());

    /**
     * Reads a Library from a XML file
     * @param inputSource
     * @return
     */
    public static Library parseXMLLibrary(InputStream inputSource) throws IOException
    {
        try
        {
            Library ret = new Library();
            /**
             * Build a DOM tree of the document
             */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputSource);
            /**
             * Gather the data
             */
            Element root = doc.getDocumentElement();
            //Parse the books
            NodeList bookList = root.getElementsByTagName("book");

            int i = 0;
            for (; i < bookList.getLength(); i++)
            {
                ret.addBook(getBook((Element) bookList.item(i)));

            }
            //Log how many books have been read
            if (logger.isDebugEnabled())
            {
                logger.log(Level.DEBUG, "parseXMLLibrary(): Read " + i + " books");
            }
            //Parse the orders
            //TODO implement order parsing
            NodeList orderList = root.getElementsByTagName("order");
            //Return the populated library
            return ret;
        }
        catch (SAXException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        catch (ParserConfigurationException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        return null;

    }

    private static Book getBook(Element bookElement)
    {
        logger.setLevel(Level.DEBUG);
        Book ret = new Book(); //Returned at the end
        //Set the ISBN
        if (logger.isDebugEnabled())
        {
            logger.log(Level.DEBUG, "Parsing ISBN attribute with value: " + bookElement.getAttribute("isbn"));
        }
        ret.setIsbn(bookElement.getAttribute("isbn"));
        /**
         * Iterate over the children to get all other attributes
         */
        NodeList childNodes = bookElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Node node = childNodes.item(i);
            if (node.getNodeName().equals("title"))
            {
                //Log a debugging message if enabled
                if (logger.isDebugEnabled())
                {
                    logger.log(Level.DEBUG, "Parsing title node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setTitle(node.getTextContent());
            }
            if (node.getNodeName().equals("price"))
            {
                //Log a debugging message if enabled
                if (logger.isDebugEnabled())
                {
                    logger.log(Level.DEBUG, "Parsing price node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setPrice(Double.parseDouble(node.getTextContent()));
            }
            if (node.getNodeName().equals("count"))
            {
                //Log a debugging message if enabled
                if (logger.isDebugEnabled())
                {
                    logger.log(Level.DEBUG, "Parsing count node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setCount(Integer.parseInt(node.getTextContent()));
            }
            if (node.getNodeName().equals("comment"))
            {
                //Log a debugging message if enabled
                if (logger.isDebugEnabled())
                {
                    logger.log(Level.DEBUG, "Parsing comment with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setComment(node.getNodeValue());
            }
        }
        return ret;
    }
}
