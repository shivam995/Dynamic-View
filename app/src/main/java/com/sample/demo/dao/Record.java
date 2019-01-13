package com.sample.demo.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("age")
    @Expose
    public String age;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("address")
    @Expose
    public String address;

}