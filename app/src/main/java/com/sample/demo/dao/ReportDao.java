package com.sample.demo.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportDao {

    @SerializedName("field-name")
    @Expose
    public String fieldName;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("min")
    @Expose
    public Integer min;
    @SerializedName("max")
    @Expose
    public Integer max;
    @SerializedName("required")
    @Expose
    public Boolean required;
    @SerializedName("options")
    @Expose
    public List<String> options = null;

}