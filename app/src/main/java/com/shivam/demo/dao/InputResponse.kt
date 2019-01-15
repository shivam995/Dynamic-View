package com.shivam.demo.dao

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Class for accessing input data, converting JsonArray directly to POJO class
 *
 * change Json input here to change views in Activity
 */
class InputResponse {

    companion object {
        private val input = "[{\n" +
                "\t\t\"field-name\": \"name\",\n" +
                "\t\t\"type\": \"text\",\n" +
                "\t\t\"required\": true\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"field-name\": \"Zone\",\n" +
                "\t\t\"type\": \"dropdown\",\n" +
                "\t\t\"options\": [\"A\", \"B\", \"C\", \"D\"]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"field-name\": \"age\",\n" +
                "\t\t\"type\": \"number\",\n" +
                "\t\t\"required\": true\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"field-name\": \"address\",\n" +
                "\t\t\"type\": \"composite\",\n" +
                "\t\t\"fields\": [{\n" +
                "\t\t\t\t\"field-name\": \"Address line1\",\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"required\": true\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"field-name\": \"Address line2\",\n" +
                "\t\t\t\t\"type\": \"text\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"field-name\": \"City\",\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"required\": true\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"field-name\": \"State\",\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"required\": true\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t],\n" +
                "\t\t\"required\": false\n" +
                "\t}\n" +
                "]"


        fun getInputData(): List<ReportDao> {
            val listType = object : TypeToken<List<ReportDao>>() {

            }.type
            return Gson().fromJson(input, listType)
        }
    }
}