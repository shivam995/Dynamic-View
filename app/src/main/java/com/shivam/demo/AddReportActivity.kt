package com.shivam.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.shivam.demo.utils.BaseView
import com.shivam.demo.utils.ViewFactory
import com.shivam.demo.dao.InputResponse
import com.shivam.demo.dao.LocalSavedReports
import com.shivam.demo.dao.Record
import com.shivam.demo.dao.ReportDao
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_report.*

class AddReportActivity : AppCompatActivity() {

    private var inputViewList: List<ReportDao>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_report)
        inputViewList = InputResponse.getInputData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Report"

        addViews()

        btnAddReport.setOnClickListener {
            validateInput()
        }

    }

    private fun validateInput() {
        val length = inputViewList?.size!!
        var isValidForm = true
        var responseObject = JsonObject()
        for (i in 0 until length) {
            val data = inputViewList!![i]
            val childView: BaseView = container.getChildAt(i) as BaseView

            if (data.required != null && data.required!! && TextUtils.isEmpty(childView.value)) {
                Toast.makeText(this, "Value cannot be empty for " + data.fieldName, Toast.LENGTH_SHORT).show()
                isValidForm = false
                return
            }

            if (data.min != null && data.max != null) {
                if (TextUtils.isEmpty(childView.value) || childView.value.toInt() < data.min!! || childView.value.toInt() > data.max!!) {
                    val message = data.fieldName + " should be between " + data.min + "-" + data.max
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    isValidForm = false
                    return
                }
            }

            responseObject.addProperty(data.fieldName, childView.value)
        }

        if (isValidForm) {
            LocalSavedReports.getInstance().saveReport(Gson().fromJson(responseObject, Record::class.java))
            Toast.makeText(this, "Report saved successfully", Toast.LENGTH_SHORT).show()
            finish()


        }

    }

    /**
     * Adding view to LinearLayout dynamically based on Json input
     */
    private fun addViews() {
        val length = inputViewList?.size!!

        for (i in 0 until length) {
            container.addView(ViewFactory.getView(this, inputViewList!![i]))
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
