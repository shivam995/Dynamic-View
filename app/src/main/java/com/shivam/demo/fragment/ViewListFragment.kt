package com.shivam.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shivam.demo.R
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.LocalSavedReports
import com.shivam.demo.dao.ReportDao
import com.shivam.demo.utils.IFragmentChangeListener
import com.shivam.demo.utils.IValueObserver
import com.shivam.demo.utils.ViewFactory
import kotlinx.android.synthetic.main.fragment_viewlist.*


private const val ARG_PARAM1 = "list"
private const val ARG_PARAM2 = "field-key"

class ViewListFragment : Fragment() {
    var dataList: ArrayList<ReportDao>? = null
    private var fieldName: String? = null
    private var fragmentChangeListener: IFragmentChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentChangeListener = activity as IFragmentChangeListener
        arguments?.let {
            dataList = it.getSerializable(ARG_PARAM1) as ArrayList<ReportDao>
            fieldName = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_viewlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViews()

        btnAddReport.setOnClickListener {
            if (validate(dataList!!)) {
                val response = getData(dataList!!, JsonObject())
                if (TextUtils.isEmpty(fieldName)) {
                    Log.i("Response: ", Gson().toJson(response))
                    Toast.makeText(context, "Record saved", Toast.LENGTH_SHORT).show()
                    LocalSavedReports.getInstance().saveReport(response)
                    fragmentChangeListener?.popFragment()
                }else{
                    fragmentChangeListener?.popFragment()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.actionBar?.hide()
    }

    /**
     * reset subList Data on fragment backPress (Data not saved)
     */
    fun resetData(reportList: MutableList<ReportDao>?) {
        for(i in 0 until reportList?.size!!){
            reportList[i].value = null
        }
    }

    /**
     * Adding view to LinearLayout dynamically based on Json input
     */
    private fun addViews() {
        val length = dataList?.size!!

        for (i in 0 until length) {
            val view = ViewFactory.getView(context!!, dataList!![i], IValueObserver { value ->
                dataList!![i].value = value
            })

            if (view is TextView) {
                view.setOnClickListener {
                    val arg = Bundle()
                    arg.putSerializable(ARG_PARAM1, dataList!![i].fields)
                    arg.putSerializable(ARG_PARAM2, dataList!![i].fieldName)
                    fragmentChangeListener?.pushFragment(Constants.FRAGMENT_ADD_RECORD, arg)
                }
            }

            container.addView(view)
        }
    }


    private fun validate(list: List<ReportDao>): Boolean {
        var isValid = true
        for (i in 0 until list.size) {
            val item = list[i]
            val type = item.type
            if (type.equals(Constants.COMPOSITE, true) && item.required != null && item.required == true) {
                validate(item.fields!!)
            } else if (item.required != null && item.required == true && TextUtils.isEmpty(item.value)) {
                Toast.makeText(context, "Enter value for " + item.fieldName, Toast.LENGTH_LONG).show()
                isValid = false
                break
            } else if (!TextUtils.isEmpty(item.value)) {
                if (type.equals(Constants.NUMBER) && ((item.min != null && item.value?.toInt()!! < item.min!!) || (item.max != null && item.value?.toInt()!! < item.max!!))) {
                    Toast.makeText(
                        context,
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


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) =
            ViewListFragment().apply {
                arguments = bundle
            }
    }



}
