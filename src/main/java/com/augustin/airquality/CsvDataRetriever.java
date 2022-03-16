package com.augustin.airquality;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.augustin.airquality.CsvEnum;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CsvDataRetriever {
    
    private static final Logger logger = LogManager.getLogger(CsvDataRetriever.class);

    public static List<Map<CsvEnum, String>> getInfoTableFromURL(String targetURL) throws MalformedURLException {
        // we store our data in combinedInfo with the schema
        // { 2022/03/09 03:00:00: [{ 0: 2022/03/09 03:00:00 }, { 1: 2022/03/09 04:00:00 }, ... ] }
        final List<Map<CsvEnum, String>> combinedInfo = new ArrayList<>();
        URL url = new URL(targetURL);

        CSVFormat csvFormat = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase();

        try(CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for(CSVRecord csvRecord : csvParser) {
                final Map<CsvEnum, String> info = new TreeMap<>();
                // use CsvEnum id for getting all info from csv
                for (CsvEnum csvElementId : CsvEnum.values()) {
                    try {
                        info.put(csvElementId, csvRecord.get(csvElementId.getCsvHeaderName()));
                    } catch (IllegalArgumentException e) {
                        info.put(csvElementId, "N/A");
                    }
                }
                combinedInfo.add(info);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return combinedInfo;
    }
}
