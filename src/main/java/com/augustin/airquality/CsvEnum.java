package com.augustin.airquality;

public enum CsvEnum {
    START_DATE(0),
    END_DATE(1),
    ORGANISM(2),
    CODE_ZAS(3),
    ZAS(4),
    CODE_SITE(5),
    SITE_NAME(6),
    IMPLANTATION_TYPE(7),
    POLLUANT(8),
    INFLUENCE_TYPE(9),
    DISCRIMINANT(10),
    REGLEMENTAIRE(11),
    TYPE_EVAL(12),
    VALUE(13),
    UNIT(14),
    TX_SAISIE(15),
    COUV_TMP(16),
    QUALITY_CODE(17),
    VALIDITY(18);

    private final int index;
    private CsvEnum(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
}
