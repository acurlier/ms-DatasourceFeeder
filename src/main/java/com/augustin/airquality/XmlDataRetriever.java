package com.augustin.airquality;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class CsvParser {

    public static getInfoTableFromURL(String targetURL) throws MalformedURLException {

        URL url = new URL(targetURL);

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        try(CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for(CSVRecord csvRecord : csvParser) {
                String firstName = csvRecord.get("First Name");
                String lastName = csvRecord.get("Last Name");
                String email = csvRecord.get("Email");
                String phoneNumber = csvRecord.get("Phone Number");

                System.out.println(firstName + "," + lastName + "," + email + "," + phoneNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
