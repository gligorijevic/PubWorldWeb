/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Djordje
 */
public class ICDE14Crawler {

    public static void getPapersAndAutors(Document doc) {
        Elements radovi = doc.select("h1");
        for (Element element : radovi) {

//            System.out.println(element.tag());
            //find healdine with Papers
            if (element.text().contains("Research Track Papers") && element.tagName().equals("h1")) {
                Element naslovi = element.nextElementSibling().nextElementSibling();

                while (naslovi != null) {
                    if (naslovi.tagName().equals("h1") || naslovi.tagName().equals("b") || naslovi.tagName().equals("ul")) {
                        String text = naslovi.text().trim();

                        //if paper name
                        if (Character.isDigit(text.charAt(0))) {
                            String[] reci = text.split(" ");
                            int skrati = reci[0].length();
                            System.out.println("PAPER: " + text.substring(skrati).trim());
                        } else if (naslovi.tagName().equals("ul")) {

                            int pocetak = 0;
                            int sredina = 0;
                            int kraj = 0;

                            while (text.length() > 3) {
                                sredina = text.indexOf("(");
                                kraj = text.indexOf(")");
                                String ime = text.substring(pocetak, sredina).trim();
                                pocetak = kraj + 2;
                                String afilijacija = text.substring(sredina + 1, kraj).trim();

                                System.out.println("AUTOR NAME: " + ime.trim());
                                System.out.println("AUTOR AFFILIATION: " + afilijacija.trim());

                                if (text.length() < pocetak) {
                                    break;
                                }
                                text = text.substring(pocetak);
                                pocetak = 0;
                                sredina = 0;
                                kraj = 0;
                            }
                            System.out.println("#########################################");
                        }
                    }

                    naslovi = naslovi.nextElementSibling();
                }
            }
        }
    }

    public static void getProgramCommitee(Document doc) {
        Elements radovi = doc.select("li");
        for (Element element : radovi) {

            String text = element.text().trim();
//            System.out.println(text);
            if (text.contains("(") && text.contains(")")) {
                int sredina = text.indexOf("(");
                int kraj = text.indexOf(")");
                int pocetak = 0;
                String ime = text.substring(pocetak, sredina).trim();
                pocetak = kraj + 2;
                String afilijacija = text.substring(sredina + 1, kraj).trim();

                System.out.println("AUTOR NAME: " + ime.trim());
                System.out.println("AUTOR AFFILIATION: " + afilijacija.trim());
            }
        }
    }
}
