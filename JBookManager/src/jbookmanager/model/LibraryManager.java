/**
 * Manages the lifetime of Library objects
 */
package jbookmanager.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uli
 */
public abstract class LibraryManager
{

    public static Library readLibrary(String file) throws FileNotFoundException
    {
        FileInputStream fin = new FileInputStream(file);
        XMLDecoder dec = new XMLDecoder(fin);
        dec.close();
        return (Library) dec.readObject();
    }

    public static void writeLibrary(Library lib, String file)
    {
        FileOutputStream fout = null;
        try
        {
            fout = new FileOutputStream(file);
            XMLEncoder enc = new XMLEncoder(fout);
            enc.writeObject(lib);
            enc.close();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
