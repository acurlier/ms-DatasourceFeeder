package com.augustin.airquality;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DbBuilder {

    private static final Logger logger = LogManager.getLogger(CsvDataRetriever.class);

    public static void buildDatabase(String url) {

        try {
            List<Map<CsvEnum, String>> combinedInfo = CsvDataRetriever.getInfoTableFromURL(url);
            pushToDb(combinedInfo);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
    }

    public static void pushToDb(List<Map<CsvEnum, String>> dataToBePushed) {
        // You can generate a Token from the "Tokens Tab" in the UI
        String token = "4N6L-eUISaecDxK4fKSWkaZ-CJkyiV6sOqPp-N-GebCeC9dmfb21vmnUh1aXuI0FtPVIbxUYCKkZE3bKlUendw==";
        String bucket = "airData";
        String org = "airquality";

        InfluxDBClient client = InfluxDBClientFactory.create("http://127.0.0.1:8086", token.toCharArray());

        List<Point> points = new ArrayList<>();

        for (Map<CsvEnum, String> row : dataToBePushed) {
            try {
                points.add(csvRowToPoint(row));
            } catch (IllegalArgumentException argumentException) {
                logger.info(argumentException.getMessage());
            }
        }

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoints(bucket, org, points);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static Point csvRowToPoint(Map<CsvEnum, String> row) {
        if (!row.get(CsvEnum.VALUE).isEmpty()) {
            Point point = Point
                    .measurement(row.get(CsvEnum.POLLUANT))
                    .addField(CsvEnum.VALUE.getTag(), Double.valueOf(row.get(CsvEnum.VALUE)))
                    .time(convertCsvEndTimeToInfluxTime(row.get(CsvEnum.END_DATE)), WritePrecision.NS);

            for (CsvEnum csvColumn : CsvEnum.values()) {
                if (csvColumn.isInInfluxAsTag()) {
                    point.addTag(csvColumn.getTag(), row.get(csvColumn));
                }
            }
            return point;
        } else {
            throw new IllegalArgumentException(String.format("Value %s can't be empty", CsvEnum.VALUE.getCsvHeaderName()));
        }
    }

    /**
     * Convert date into instant
     * @param date (2022/03/09 01:00:00)
     * @return instant date
     */
    public static Instant convertCsvEndTimeToInfluxTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime.toInstant(ZoneOffset.UTC);
    }
}
