package com.augustin.airquality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final String ROOT_URL =
            "https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/";


    public static void main(String[] args) {
        logger.debug("Hello in CSV importer");
        DbBuilder.buildDatabase(ROOT_URL);

    }
}
  
