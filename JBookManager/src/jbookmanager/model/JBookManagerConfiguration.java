/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uli
 */
public class JBookManagerConfiguration
{

    private static final String configFilename = ".jbookmanager";
    private Properties props;

    public JBookManagerConfiguration()
    {
        //File exists, so load the data
        FileInputStream fin = null;
        try
        {
            //No action is required
            props = new Properties();
            //Check if the file exists
            File configFile = new File(configFilename);
            if (!configFile.exists())
            {
                return; //No action is required
            }
            //File exists, so load the data
            fin = new FileInputStream(configFile);
            props.loadFromXML(fin);
        }
        catch (IOException ex)
        {
            Logger.getLogger(JBookManagerConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fin.close();
            }
            catch (NullPointerException ex)
            {
            }
            catch (IOException ex)
            {
                Logger.getLogger(JBookManagerConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getProperty(String key)
    {
        return props.getProperty(key, "");
    }

    public void setProperty(String key, String value)
    {
        props.setProperty(key, value);
    }

    /**
     * Saves the configuration data when the instance is to be deleted
     */
    @Override
    protected void finalize()
    {
        try
        {
            save();
        }
        catch (IOException ex)
        {
            Logger.getLogger(JBookManagerConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() throws IOException
    {
        FileOutputStream fout = new FileOutputStream(configFilename);
        props.storeToXML(fout, null);
        fout.close();
    }
}
