package com.augustin.airquality;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CsvDataRetrieverTest {
    @Test
    public void testGetInfoTableFromURL() {

        try {
            final List<Map<CsvEnum, String>> combinedInfo = CsvDataRetriever.getInfoTableFromURL("https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/2022/FR_E2_2022-03-09.csv");
            System.out.println(String.format("Size of CSV : %s", combinedInfo.size()));

            // for (Map<CsvEnum, String> entryCombinedInfo : combinedInfo) {
            //     System.out.println(String.format("##########################"));
            //     System.out.println();
            //     for (Map.Entry<CsvEnum, String> entryInfo: entryCombinedInfo.entrySet()) {
            //         System.out.println(String.format("%s : %s", entryInfo.getKey(), entryInfo.getValue()));
            //     }
            //     System.out.println(String.format("##########################"));
            //     System.out.println("");
            // }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
