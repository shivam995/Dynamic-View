package com.shivam.demo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonArray
import com.shivam.demo.R
import com.shivam.demo.adapters.ReportListAdapter
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.InputResponse
import com.shivam.demo.dao.LocalSavedReports
import com.shivam.demo.utils.IFragmentChangeListener
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var reportList: JsonArray? = null
    private var adapter: ReportListAdapter? = null
    private var fragmentChangeListener: IFragmentChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentChangeListener = activity as IFragmentChangeListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reportList = LocalSavedReports.getInstance().savedReport
        val inputResponse = InputResponse.getInputData()
        activity?.actionBar?.show()

        adapter = ReportListAdapter(reportList!!, context!!)
        rvReport.adapter = adapter
        rvReport.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvReport.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


        btnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("list", inputResponse)
            fragmentChangeListener?.pushFragment(Constants.FRAGMENT_ADD_RECORD, bundle)
        }

    }

    override fun onResume() {
        super.onResume()
        reportList = LocalSavedReports.getInstance().savedReport
        adapter?.notifyDataSetChanged()
        tvTotalReports.text = "Total Reports: " + reportList!!.size()
    }


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) =
            HomeFragment().apply {
                arguments = bundle
            }
    }
}
