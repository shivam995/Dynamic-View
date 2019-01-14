package com.shivam.demo.utils;

public enum ViewType {

    TEXT("text"),
    NUMBER("number"),
    DROPDOWN("dropdown"),
    MULTILINE("multiline");


    private String type;

    ViewType(String text) {
        this.type = text;
    }
}
