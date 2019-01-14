package com.shivam.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.shivam.demo.dao.LocalSavedReports
import com.shivam.demo.dao.Record
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private var reportList: List<Record>? = null
    private var adapter: ReportListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Dashboard"
        reportList = LocalSavedReports.getInstance().savedReport

        adapter = ReportListAdapter(reportList!!, this)
        rvReport.adapter = adapter
        rvReport.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvReport.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddReportActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        reportList = LocalSavedReports.getInstance().savedReport
        adapter?.notifyDataSetChanged()

        tvTotalReports.text = "Total Reports: " + reportList!!.size
    }
}
