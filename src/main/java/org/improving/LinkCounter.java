package org.improving;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LinkCounter {
    public static void main(String[] args) throws IOException {
        Set<String> linkSet = new HashSet<>();
        getLinks("http://improving.com", linkSet);
    }
    public static void getLinks(String url, Set<String> linkSet) throws IOException {
        // need http protocol
        Document doc = Jsoup.connect(url).get();
        // get all links
        Elements links = doc.select("a[href]");
        for (Element link: links) {
            String l = removeHash(link);

            if(l.startsWith("https://improving.com")) {
                if(!linkSet.contains(l)){
                    linkSet.add(l);
                    System.out.println(l);
                    try {
                        getLinks(l, linkSet);
                    }catch (HttpStatusException ex){
                        System.out.println("Status 404");
                        linkSet.remove(l);
                    }catch (UnsupportedMimeTypeException ex){
                        System.out.println("PDF");
                    }
                }
            }
        }
        System.out.println("Number of unique links= " + linkSet.size());
    }

    private static String removeHash(Element link) {
        var l = link.attr("abs:href");
        if(l.contains("#")){
            var linkSplit = l.split("#");
            return linkSplit[0];
        }
        return l;
    }

}
