/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 *
 * @author uli
 */
public class AmazonBookInfo
{
        
    public static Book getBookInfo(String isbn) throws IOException, IllegalStateException
    {
        HttpClient client = new HttpClient();

        client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                                        new DefaultHttpMethodRetryHandler());
        HttpMethod method =
                new GetMethod("http://amazon.de/s/ref=nb_ss_b?__mk_de_DE=%C5M%C5Z%D5%D1&url=search-alias%3Dstripbooks&field-keywords=" +
                isbn + "&x=0&y=0");
        client.executeMethod(method);
        
        String html = method.getResponseBodyAsString();

        Book b = new Book();
        //Get the title
        Matcher m = titlePattern.matcher(html);
        m.find();
        String title = m.group(1);
        b.setTitle(title);
        //Get the price
        m = pricePattern.matcher(html);
        m.find();
        double price = new Double(m.group(1).replace(',', '.'));
        b.setPrice(price);
        //Get the publisher
        m = publisherPattern.matcher(html);
        m.find();
        b.setComment(m.group(1));
 
        return b;
    }

    //Regex patterns
    private static final Pattern titlePattern = Pattern.compile("<span id=\"btAsinTitle\" >(.+?)</span>");
    private static final Pattern pricePattern = Pattern.compile("<b class=\"priceLarge\">EUR ([\\d,]+) ?</b>");
    private static final Pattern publisherPattern = Pattern.compile("<li><b>Verlag\\:</b> ([^<]+)</li>");
}
