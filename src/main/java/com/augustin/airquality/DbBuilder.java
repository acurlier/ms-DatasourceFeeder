package com.augustin.airquality;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
        String token = "mBEF7dGICSwqpO06Lw-UOcNB60fYaMdzV9Rf3p1L-HygqMXExg-ogVH67ipIzxMLI_CkXJWAus1C2ANqxKQ0Ig==";
        String bucket = "airData";
        String org = "airquality";

        InfluxDBClient client = InfluxDBClientFactory.create("http://127.0.0.1:8086", token.toCharArray());

        List<Point> points = new ArrayList<>();

        for (Map<CsvEnum, String> row : dataToBePushed) {
            points.add(csvRowToPoint(row));
        }

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoints(bucket, org, points);
        }
    }

    public static Point csvRowToPoint(Map<CsvEnum, String> row) {
        Point point = Point
                .measurement("pollutant")
                .addField(CsvEnum.VALUE.getTag(), row.get(CsvEnum.VALUE))
                .addField(CsvEnum.UNIT.getTag(), row.get(CsvEnum.UNIT))
                .time(Instant.now(), WritePrecision.NS);

        for (CsvEnum csvColumn : CsvEnum.values()) {
            if (csvColumn != CsvEnum.VALUE && csvColumn != CsvEnum.UNIT) {
                point.addTag(csvColumn.getTag(), row.get(csvColumn));
            }
        }

        return point;
    }
}
