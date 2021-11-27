package com.genesiscode.practiceasofour.models.utils;

public enum Category {
    TD("Todos diferentes"),
    _1P("Exactamente un par"),
    T("Tercia"),
    _2P("2 pares"),
    P("Poker"),
    TP("1 Tercia y 1 par"),
    Q("Quintilla");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
