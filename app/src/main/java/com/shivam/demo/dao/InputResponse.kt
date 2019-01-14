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
        private val input = "[{'field-name':'name', 'type':'text','required':false},\n" +
                "\n" +
                "{'field-name':'age', 'type':'number', 'min':18, 'max':65},\n" +
                "\n" +
                "{'field-name':'gender', 'type':'dropdown', 'options':['male', 'female', 'other']},\n" +
                "\n" +
                "{'field-name':'address', 'type':'multiline'}\n" +
                "\n" +
                "]"


        fun getInputData(): List<ReportDao> {
            val listType = object : TypeToken<List<ReportDao>>() {

            }.type
            return Gson().fromJson(input, listType)
        }
    }
}