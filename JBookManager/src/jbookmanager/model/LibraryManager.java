/**
 * Manages the lifetime of Library objects
 */
package jbookmanager.model;

import jbookmanager.model.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author uli
 */
public abstract class LibraryManager
{

    /**
     * Validates the XML document against the XSD stylesheet
     * @param documentFilename The name of the XML file
     * @param stylesheetFilename The name of the XSD stylesheet file
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException 
     */
    public static void validateXML(String documentFilename, StreamSource stylesheetSource) throws SAXException, IOException, ParserConfigurationException
    {
            // Parse an XML document into a DOM tree.
            DocumentBuilder parser =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(new File(documentFilename));

            // Create a SchemaFactory capable of understanding WXS schemas.
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Load a WXS schema, represented by a Schema instance.
            Schema schema = factory.newSchema(stylesheetSource);

            // Create a Validator object, which can be used to validate
            // an instance document.
            Validator validator = schema.newValidator();

            // Validate the DOM tree.
            validator.validate(new DOMSource(document));
    }
    /**
     * This represents the library opened at this time
     */
    public static Library library = new Library();
    public static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public static Library readLibrary(String file) throws FileNotFoundException
    {
        InputStream fin = null;
        try
        {
            fin = new GZIPInputStream(new FileInputStream(file));
            XMLDecoder dec = new XMLDecoder(fin);
            dec.close();
            return (Library) dec.readObject();
        }
        catch (IOException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        finally
        {
            try
            {
                fin.close();
            }
            catch (IOException ex)
            {
                logger.log(Level.ERROR, null, ex);
            }
        }
        return null;
    }

    public static void writeLibrary(Library lib, String file)
    {
        OutputStream fout = null;
        try
        {
            fout = new GZIPOutputStream(new FileOutputStream(file));
            XMLEncoder enc = new XMLEncoder(fout);
            enc.writeObject(lib);
            enc.close();
        }
        catch (IOException ex)
        {
            logger.log(Level.ERROR, null, ex);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                logger.log(Level.ERROR, null, ex);
            }
        }
    }

    public static Logger logger = Logger.getLogger(LibraryManager.class);
}
