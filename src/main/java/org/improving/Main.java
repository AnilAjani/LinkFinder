package org.improving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, String> hrefMap = new HashMap<String, String>();

        try {
            // need http protocol
           Document doc = Jsoup.connect("http://improving.com").get();


            // get all links
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                var leftSide = removeHash(link);
                // hrefMap.put(link.attr("href"), link.text());
                if(leftSide.startsWith("https://improving.com") && !hrefMap.keySet().contains(leftSide)){
                    hrefMap.put(leftSide, link.text());
                    System.out.println(leftSide);
                }
                for(int i = 0; i < hrefMap.size(); i++){

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("size of map" + hrefMap.size());
    }

    private static String removeHash(Element link) {
        var l = link.attr("abs:href");
        var linkSplit = l.split("#");
        return linkSplit[0];
    }
}