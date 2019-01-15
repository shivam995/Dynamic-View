package com.shivam.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shivam.demo.adapters.RecordListAdapter
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.InputResponse
import com.shivam.demo.dao.LocalSavedReports
import com.shivam.demo.dao.ReportDao
import kotlinx.android.synthetic.main.activity_add_report.*

class AddReportActivity : AppCompatActivity() {

    private var inputViewList: List<ReportDao>? = null
    private var listAdapter: RecordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_report)
        inputViewList = InputResponse.getInputData()
        supportActionBar?.hide()

        addViews()

        btnAddReport.setOnClickListener {
            if (validate(inputViewList!!)) {
                val response = getData(inputViewList!!, JsonObject())
                Log.i("Response: ", Gson().toJson(response))
                Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show()
                LocalSavedReports.getInstance().saveReport(response)

                onBackPressed()
            }
        }

    }

    private fun addViews() {
        listAdapter = RecordListAdapter(this, inputViewList!!)
        rvDataList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDataList.adapter = listAdapter


    }


    private fun validate(list: List<ReportDao>): Boolean {
        var isValid = true
        for (i in 0 until list.size) {
            val item = list[i]
            val type = item.type
            if (type.equals(Constants.COMPOSITE, true)) {
                validate(item.fields!!)
            } else if (item.required != null && item.required == true && TextUtils.isEmpty(item.value)) {
                Toast.makeText(this, "Enter value for " + item.fieldName, Toast.LENGTH_LONG).show()
                isValid = false
                break
            } else if (!TextUtils.isEmpty(item.value)) {
                if (type.equals(Constants.NUMBER) && ((item.min != null && item.value?.toInt()!! < item.min!!) || (item.max != null && item.value?.toInt()!! < item.max!!))) {
                    Toast.makeText(
                        this,
                        "Value for " + item.fieldName + " should be between " + item.min + "-" + item.max,
                        Toast.LENGTH_LONG
                    ).show()
                    isValid = false
                    break
                }
            }

        }
        return isValid
    }

    private fun getData(list: List<ReportDao>, jsonObj: JsonObject): JsonObject {
        for (i in 0 until list.size) {
            if (list[i].type.equals(Constants.COMPOSITE, true)) {
                getData(list[i].fields!!, jsonObj)
            } else if (!TextUtils.isEmpty(list[i].value)) {
                jsonObj.addProperty(list[i].fieldName, list[i].value)
            }
        }
        return jsonObj
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
