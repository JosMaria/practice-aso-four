package com.genesiscode.practicefour.models.utils;

public enum Category {
    TD("Todos diferentes"),
    _1P("Exactamente un par"),
    _2P("2 pares"),
    TP("1 Tercia y 1 par"),
    T("Tercia"),
    P("Poker"),
    Q("Quintilla");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
