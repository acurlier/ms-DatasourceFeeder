package com.augustin.airquality;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public final class CsvDataRetriever {

    public static void getInfoTableFromURL(String targetURL) throws MalformedURLException {

        URL url = new URL(targetURL);
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        try(CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for(CSVRecord csvRecord : csvParser) {
                
                String bedDate = csvRecord.get(1);
                String endDate = csvRecord.get("Date de fin");
                String org = csvRecord.get("Organisme");
                String codeZaz = csvRecord.get("code zas");
                String zaz = csvRecord.get("Zas");
                String codeSite = csvRecord.get("code site");
                String nameSite = csvRecord.get("nom site");
                String typeImplant = csvRecord.get("type d'implantation");
                String polluant = csvRecord.get("Polluant");
                String typeInfluence = csvRecord.get("type d'influence");
                String discriminant = csvRecord.get("discriminant");
                String reglementaire = csvRecord.get("Réglementaire");
                String typeEval = csvRecord.get("type d'évaluation");
                String procMeasure = csvRecord.get("procédure de mesure");
                String typeValue = csvRecord.get("type de valeur");
                String value = csvRecord.get("valeur");
                String rawValue = csvRecord.get("valeur brute");
                String unit = csvRecord.get("unité de mesure");
                String txSaisie = csvRecord.get("taux de saisie");
                String couvTemp = csvRecord.get("couverture temporelle");
                String couvData = csvRecord.get("couverture de données");
                String codeQuality = csvRecord.get("code qualité");
                String validity = csvRecord.get("validité");
                

                System.out.println(bedDate + "," + endDate + "," + org + "," + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
