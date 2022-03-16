package com.augustin.airquality;

/**
 * Enum representing Info in Csv
 * Each index is corresponding at a column in the csv
 */
public enum CsvEnum {
    START_DATE( "Date de début","start"),
    END_DATE( "Date de fin","end"),
    ORGANISM( "Organisme","organisme"),
    CODE_ZAS( "code zas","code_zas"),
    ZAS( "Zas","zas"),
    CODE_SITE( "code site","code_site"),
    SITE_NAME("nom site", "nom_site"),
    IMPLANTATION_TYPE("type d'implantation" ,"type_implantation"),
    POLLUANT( "Polluant","polluant"),
    INFLUENCE_TYPE("type d'influence", "type_influence"),
    // DISCRIMINANT(10, "discrimant"),
    // REGLEMENTAIRE(11, "reglementaire"),
    // PROCEDURE(12, "procedure_de_mesure"),
    // TYPE_OF_VALUE(13, "type_de_valeur"),
    VALUE("valeur", "valeur"),
    UNIT("unité de mesure", "unit");
//    TX_SAISIE(16, "taux_de_saisie"),
//    COUV_TMP(17, "couverture_temporelle"),
//    COUV_DATA(18, "couverture_de_données"),
//    QUALITY_CODE(19, "code_qualité"),
//    VALIDITY(20, "validite");

    private final String csvHeaderName;
    private final String tag;

    CsvEnum(String csvHeaderName, String tag) {
        this.csvHeaderName = csvHeaderName;
        this.tag = tag;
    }
    public String getCsvHeaderName() {
        return csvHeaderName;
    }
    public String getTag() { return tag; }
}
