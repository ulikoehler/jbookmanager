/**
 * Manages the lifetime of Library objects
 */
package jbookmanager.controller;

import jbookmanager.model.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author uli
 */
public abstract class LibraryManager
{

    /**
     * Configures Log4J
     */
    static
    {
        PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
        logger = Logger.getLogger(LibraryManager.class);
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
            Logger.getLogger(LibraryManager.class.getName()).log(Level.ERROR, null, ex);
        }
        finally
        {
            try
            {
                fin.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LibraryManager.class.getName()).log(Level.ERROR, null, ex);
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
            Logger.getLogger(LibraryManager.class.getName()).log(Level.ERROR, null, ex);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LibraryManager.class.getName()).log(Level.ERROR, null, ex);
            }
        }
    }

    public static Logger logger;
}
