package com.sample.demo.dao;

import java.util.ArrayList;
import java.util.List;

public class LocalSavedReports {


    private static LocalSavedReports sInstance = null;
    private List<Record> mDataList = new ArrayList<>();

    private LocalSavedReports() {
    }

    public static LocalSavedReports getInstance() {
        if (sInstance == null) {
            sInstance = new LocalSavedReports();
        }

        return sInstance;
    }


    public void saveReport(Record data) {
        mDataList.add(data);
    }


    public List<Record> getSavedReport() {
        return mDataList;
    }
}
