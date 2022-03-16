package com.augustin.airquality;

/**
 * Enum representing Info in Csv
 * Each index is corresponding at a column in the csv
 */
public enum CsvEnum {
    END_DATE( "Date de fin","end", false),
    ORGANISM( "Organisme","organisme", true),
    CODE_ZAS( "code zas","code_zas", true),
    ZAS( "Zas","zas", true),
    CODE_SITE( "code site","code_site", true),
    SITE_NAME("nom site", "nom_site", true),
    IMPLANTATION_TYPE("type d'implantation" ,"type_implantation", true),
    POLLUANT( "Polluant","polluant", false),
    INFLUENCE_TYPE("type d'influence", "type_influence", true),
    // DISCRIMINANT(10, "discrimant"),
    // REGLEMENTAIRE(11, "reglementaire"),
    // PROCEDURE(12, "procedure_de_mesure"),
    // TYPE_OF_VALUE(13, "type_de_valeur"),
    VALUE("valeur", "valeur", false),
    UNIT("unité de mesure", "unit", false);
//    TX_SAISIE(16, "taux_de_saisie"),
//    COUV_TMP(17, "couverture_temporelle"),
//    COUV_DATA(18, "couverture_de_données"),
//    QUALITY_CODE(19, "code_qualité"),
//    VALIDITY(20, "validite");

    private final String csvHeaderName;
    private final String tag;
    private final boolean inInfluxAsTag;

    CsvEnum(String csvHeaderName, String tag, boolean inInfluxAsTag) {
        this.csvHeaderName = csvHeaderName;
        this.tag = tag;
        this.inInfluxAsTag = inInfluxAsTag;
    }
    public String getCsvHeaderName() {
        return csvHeaderName;
    }
    public String getTag() { return tag; }

    public boolean isInInfluxAsTag() {
        return inInfluxAsTag;
    }
}
