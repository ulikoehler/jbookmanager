/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import jbookmanager.model.LibraryManager;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author uli
 */
public class LibraryWriterTest
{

    private Library library;

    public LibraryWriterTest()
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
        b1.setIsbn("978-24733333354");
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
    }

    /**
     * Test of writeLibrary method, of class LibraryWriter.
     */
    @Test
    public void testWriteLibrary()
    {
        try
        {
            System.out.println("writeLibrary");
            String file = "test.xml";
            LibraryWriter.writeLibraryPlain(library, file);
            //Validate the XML file
            LibraryManager.validateXML("test.xml", new StreamSource(ClassLoader.getSystemResource("Library.xsd").openStream()));
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