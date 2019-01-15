package com.shivam.demo.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class LocalSavedReports {


    private static LocalSavedReports sInstance = null;
    private JsonArray arrayList = new JsonArray();

    private LocalSavedReports() {
    }

    public static LocalSavedReports getInstance() {
        if (sInstance == null) {
            sInstance = new LocalSavedReports();
        }

        return sInstance;
    }


    public void saveReport(JsonObject data) {
        arrayList.add(data);
    }


    public JsonArray getSavedReport() {
        return arrayList;
    }
}
