package org.improving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var startTime = new Date().getTime();
        Set<String> linkSet = new HashSet<>();
        LinkCounter.getLinks("https://improving.com", linkSet);
        var endTime = new Date().getTime();
        System.out.println("It took " + (endTime - startTime) / 1000.0 + "seconds to run this freaking scraper!");
    }
}