package com.augustin.airquality;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CsvListRetrieverTest {
    @Test
    public void testRetrieveCsvList() {
        try {
            Map<String, List<String>> itemList = CsvListRetriever.retrieveCsvList();
            System.out.println(itemList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
