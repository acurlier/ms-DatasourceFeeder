package com.augustin.airquality;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class DbBuilderTest {
    @Test
    public void testBuildDatabase() {

    }

    @Test
    public void testPushToDb() {
        String url = "https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/2022/FR_E2_2022-03-09.csv";
        DbBuilder.buildDatabase(url);
    }
}
