/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author uli
 */
public class AmazonBookInfoTest {

    public AmazonBookInfoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    /**
     * Test of getBookInfo method, of class AmazonBookInfo.
     * This is highly dependent on the actual Amazon page.
     * If the test fails, check if the expected results are correct!
     * @throws IOException Forwarded from Jakarta HttpClient
     */
    @Test
    public void testGetBookInfo() throws IOException
    {
        String expectedTitle = "Bis(s) zum Ende der Nacht: Band 4 (Gebundene Ausgabe)";
        double expectedPrice = 24.90;

        System.out.println("getBookInfo");
        String isbn = "978-3551581990";
        Book result = AmazonBookInfo.getBookInfo(isbn);

        Assert.assertEquals(result.getTitle(), expectedTitle);
        Assert.assertEquals(expectedPrice, result.getPrice(), 0.05);
        //The comment can't be verified
    }

}