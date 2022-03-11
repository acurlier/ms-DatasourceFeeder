package com.augustin.airquality;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.time.Instant;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;

public class DbBuilder {

    private static final Logger logger = LogManager.getLogger(CsvDataRetriever.class);

    public static void buildDatabase() {
        
        String url = "https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/2022/FR_E2_2022-03-09.csv";        
        
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
        
        Point point = Point
        .measurement("mem")
        .addTag("host", "host1")
        .addField("used_percent", 23.43234543)
        .time(Instant.now(), WritePrecision.NS);
      
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoint(bucket, org, point);
        }
    }    
    
}
