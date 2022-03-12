package com.augustin.airquality;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App 
{
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final String ROOT_URL =  "https://files.data.gouv.fr/lcsqa/concentrations-de-polluants-atmospheriques-reglementes/temps-reel/";
    
    public static void main( String[] args ) {
      System.out.println("Hello World");
    }
}
  
