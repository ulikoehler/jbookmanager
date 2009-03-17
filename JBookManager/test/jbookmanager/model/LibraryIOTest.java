/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import jbookmanager.model.LibraryManager;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Test the reading and writing of XML library files
 * @author uli
 */
public class LibraryIOTest
{

    private Library library;
    /**
     * Options
     */
    private static final boolean deleteFile = true; //If true, delete the output file after the tests

    public LibraryIOTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
        /**
         * Add some test books to the library
         */
        library = new Library();
        Book b1 = new Book();
        b1.setIsbn("978-2473333354");
        b1.setTitle("Just a test book");
        b1.setPrice(109.22);
        b1.setCount(5);
        b1.setComment("This is just a test book");
        library.addBook(b1);
        
        Book b2 = new Book();
        b2.setIsbn("978-12345678905");
        b2.setTitle("Just Another test book");
        b2.setPrice(12.95);
        b2.setCount(6);
        b2.setComment("This is yet another test book");
        library.addBook(b2);
        /**
         * Add some test orders to the library
         */
        OrderManager orderManager = new OrderManager();
        Order testOrder1 = new Order("Test order");
            testOrder1.addAtomicOrder("978-12345678905", 157);
        orderManager.addOrder(testOrder1);
        Order testOrder2 = new Order("Test order 2");
            testOrder2.addAtomicOrder("978-12345678905", 157);
            testOrder2.addAtomicOrder("978-2473333354", 1041);
        orderManager.addOrder(testOrder2);
        library.setOrderManager(orderManager);

    }

    @After
    public void deleteOutputFile()
    {
        File xmlFile = new File("test.xml");
        if (xmlFile.exists() && deleteFile)
        {
            xmlFile.delete();
        }
    }

    /**
     * Test of writeLibrary method, of class LibraryWriter.
     * When the library is written:
     *  Test of parseXMLLibrary method, of class LibraryDOMReader
     */
    @Test
    public void testReadWriteLibrary()
    {
        try
        {
            System.out.println("writeLibrary");
            String file = "test.xml";
            LibraryWriter.writeLibraryPlain(library, file);
            //Validate the exported XML
            LibraryManager.validateXML("test.xml", new StreamSource(ClassLoader.getSystemResource("Library.xsd").
                    openStream()));
            //Reread the exported library and compare with the original one
            Library rereadLibrary = LibraryDOMReader.parseXMLLibrary(new FileInputStream("test.xml"));
            Assert.assertEquals("Libraries are not equal", library, rereadLibrary);
        }
        catch (SAXException ex)
        {
            TestCase.fail(ex.toString());
        }
        catch (IOException ex)
        {
            TestCase.fail(ex.toString());
        }
        catch (ParserConfigurationException ex)
        {
            TestCase.fail(ex.toString());
        }
    }
}