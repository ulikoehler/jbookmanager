/*
 * DOM may be slow when used on large XML documents and may consume incredibly much memory!
 * SAX implementation will follow if neccessary
 */
package jbookmanager.model;

import java.io.FileInputStream;
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
     * Wrapper function for parseXMLLibrary
     * @param filename The filename to read the XML data from
     * @return The parsed library
     * @throws java.io.IOException
     */
    public static Library readLibrary(String filename) throws IOException
    {
        return parseXMLLibrary(new FileInputStream(filename));
    }

    /**
     * Reads a Library from a XML file
     * @param inputSource
     * @return The parsed and populated library
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
            NodeList orderList = root.getElementsByTagName("order");

            i = 0;
            OrderManager orderManager = new OrderManager();
            for (; i < orderList.getLength(); i++)
            {
                orderManager.addOrder(getOrder((Element) orderList.item(i)));
            }
            ret.setOrderManager(orderManager);
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

    private static Order getOrder(Element orderElement)
    {
        Order ret = new Order();
        //Set the name
        ret.setName(orderElement.getAttribute("name"));
        //Get a list of all child nodes == the atomic orders
        NodeList childNodes = orderElement.getElementsByTagName("atomicOrder");

        childNodes.getLength();
        if (logger.isTraceEnabled())
        {
            logger.log(Level.TRACE, "Parsing " + childNodes.getLength() + " atomic orders for order '" + ret.getName() +
                    "'");
        }

        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Element node = (Element) childNodes.item(i);
            //Log
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "Parsing atomic order with ISBN = \"" + node.getAttribute("isbn") +
                        "\" and count = " + Integer.parseInt(node.getAttribute("count")));
            }
            //Add the atomic order to the order
            ret.addAtomicOrder(node.getAttribute("isbn"), Integer.parseInt(node.getAttribute("count")));
        }
        return ret;
    }

    private static Book getBook(Element bookElement)
    {
        Book ret = new Book(); //Returned at the end
        //Set the ISBN
        if (logger.isTraceEnabled())
        {
            logger.log(Level.TRACE, "Parsing ISBN attribute with value: " + bookElement.getAttribute("isbn"));
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
                if (logger.isTraceEnabled())
                {
                    logger.log(Level.TRACE, "Parsing title node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setTitle(node.getTextContent());
            }
            if (node.getNodeName().equals("price"))
            {
                //Log a debugging message if enabled
                if (logger.isTraceEnabled())
                {
                    logger.log(Level.TRACE, "Parsing price node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setPrice(Double.parseDouble(node.getTextContent()));
            }
            if (node.getNodeName().equals("count"))
            {
                //Log a debugging message if enabled
                if (logger.isTraceEnabled())
                {
                    logger.log(Level.TRACE, "Parsing count node with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setCount(Integer.parseInt(node.getTextContent()));
            }
            if (node.getNodeName().equals("comment"))
            {
                //Log a debugging message if enabled
                if (logger.isTraceEnabled())
                {
                    logger.log(Level.TRACE, "Parsing comment with value: " + node.getTextContent());
                }
                //Save the data in the Book object
                ret.setComment(node.getTextContent());
            }
        }
        return ret;
    }
}
