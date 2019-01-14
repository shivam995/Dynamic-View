package com.shivam.demo.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Record : Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("age")
    @Expose
    var age: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null

}