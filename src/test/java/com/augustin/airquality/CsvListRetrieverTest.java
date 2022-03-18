package com.augustin.airquality;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CsvListRetrieverTest {
    private static final String ROOT_URL =
            "https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/";

    @Test
    public void testRetrieveCsvList() {
        try {
            Map<String, List<String>> itemList = CsvListRetriever.retrieveCsvList(ROOT_URL);
            System.out.println(itemList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
