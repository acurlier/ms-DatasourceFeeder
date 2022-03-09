package com.augustin.airquality;

import java.net.MalformedURLException;

import org.junit.Test;

public class CsvDataRetrieverTest {
    @Test
    public void testGetInfoTableFromURL() {

        try {
            CsvDataRetriever.getInfoTableFromURL("https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/2022/FR_E2_2022-03-09.csv");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
