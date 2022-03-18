package com.augustin.airquality;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CsvListRetriever {
    private static final Logger logger = LogManager.getLogger(CsvListRetriever.class);

    public static Map<String, List<String>> retrieveCsvList(String url) throws IOException {

        Document root;
        Map<String,List<String>> yearlyData = new HashMap<>();

        try {
            root = getDocument(url);

        } catch (IOException e) {
            logger.error(e.getMessage());
            // TODO : que faire en cas d'erreur de ce type (on retourne un set vide ??)
            return yearlyData;
        }
        // get the links to the index of each year
        Elements yearLinks = root.select("pre > a");
        for (Element element : yearLinks) {
            // for each year, retrieve the link to all csv dataset
            String yearAddress = element.absUrl("href");
            if(url.equals(yearAddress)) {
                continue;              
            }
            try {
                Document yearDoc = getDocument(yearAddress);
                yearlyData.put(yearAddress.replace(url, "").replace("/", ""), getCsvUrl(yearDoc));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return yearlyData;
    }
        
    private static Document getDocument(final String address) throws IOException {
        // retrieve home data page
        Document doc;
        doc = Jsoup.connect(address).get();
        return doc;
    }

    private static List<String> getCsvUrl(final Document yearDoc) throws IOException {
        List<String> resultBuffer = new ArrayList<>();
        for (Element element : yearDoc.select("pre > a")) {
            if (element.absUrl("href").endsWith(".csv")) {
                resultBuffer.add(element.absUrl("href"));
            }      
        }
        return resultBuffer;
    }
}
