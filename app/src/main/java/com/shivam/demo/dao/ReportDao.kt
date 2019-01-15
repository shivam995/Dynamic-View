package com.shivam.demo.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ReportDao : Serializable {

    @SerializedName("field-name")
    @Expose
    var fieldName: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("min")
    @Expose
    var min: Int? = null
    @SerializedName("max")
    @Expose
    var max: Int? = null
    @SerializedName("required")
    @Expose
    var required: Boolean? = null
    @SerializedName("options")
    @Expose
    var options: List<String>? = null
    @SerializedName("fields")
    @Expose
    var fields: List<ReportDao>? = null

    var value:String? = null
}