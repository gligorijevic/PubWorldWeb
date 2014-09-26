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

/**
 *
 * @author Djordje
 */
public class PubWorld {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Document doc1 = Jsoup.connect("http://ieee-icde2014.eecs.northwestern.edu/accepted.html").get();
        Document doc2 = Jsoup.connect("http://ieee-icde2014.eecs.northwestern.edu/pc.html").get();
        Document doc3 = Jsoup.connect("http://www.icde2013.org/papers.html").get();
        Document doc4 = Jsoup.connect("http://www.icde2013.org/pc.html").get();
        
//        System.out.println(doc.toString());
//        ICDE14Crawler.getPapersAndAutors(doc1);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        ICDE14Crawler.getProgramCommitee(doc2);
//                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        ICDE14Crawler.getPapersAndAutors(doc3);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        ICDE14Crawler.getProgramCommitee(doc4);
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        
    }

}
