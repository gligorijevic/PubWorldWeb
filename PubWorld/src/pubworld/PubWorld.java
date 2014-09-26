/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pubworld;

import crawler.ICDE14Crawler;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 *
 * @author Djordje
 */
public class PubWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Document doc = Jsoup.connect("http://ieee-icde2014.eecs.northwestern.edu/accepted.html").get();
//        System.out.println(doc.toString());
        ICDE14Crawler.getPapersAndAutors(doc);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        ICDE14Crawler.getProgramCommitee(doc);
     
    }

}
