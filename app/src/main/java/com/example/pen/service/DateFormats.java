package com.example.pen.service;

import java.text.SimpleDateFormat;

public enum DateFormats {
    SIMPLE_DATE(new SimpleDateFormat("dd/MM/yyyy")),
    DATE_TIME(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private final SimpleDateFormat format;
    DateFormats(SimpleDateFormat format){
        this.format=format;
    }

    public SimpleDateFormat getFormat() {
        return format;
    }
}
