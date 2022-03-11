package com.augustin.airquality;

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

        List<Map<CsvEnum, String>> combinedInfo = new ArrayList<>();
        Map<CsvEnum, String> dummyData = new TreeMap<>();

        dummyData.put(CsvEnum.START_DATE, "11/03/22");
        dummyData.put(CsvEnum.END_DATE, "11/03/22");

        combinedInfo.add(dummyData);

        DbBuilder.pushToDb(combinedInfo);

    }
}
