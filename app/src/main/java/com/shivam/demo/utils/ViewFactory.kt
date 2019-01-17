package com.shivam.demo.utils

import android.content.Context
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.shivam.demo.R
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.ReportDao


class ViewFactory {


    companion object {

        fun getView(context: Context, reportDao: ReportDao, valueObserver: IValueObserver): View? {

            when (reportDao.type!!) {
                Constants.TEXT -> {
                    val view = CustomEditText(context, valueObserver)
                    view.hint = reportDao.fieldName
                    view.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
                    view.setBackgroundResource(R.drawable.shape_rect)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(46, 20, 46, 0)
                    view.layoutParams = params

                    return view

                }
                Constants.NUMBER -> {
                    val view = CustomEditText(context, valueObserver)
                    view.hint = reportDao.fieldName
                    view.inputType = InputType.TYPE_CLASS_NUMBER

                    view.setBackgroundResource(R.drawable.shape_rect)
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = InputFilter.LengthFilter(2)
                    view.filters = filterArray
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(46, 20, 46, 0)
                    view.layoutParams = params

                    return view
                }
                Constants.DROPDOWN -> {
                    val view = CustomSpinner(context, valueObserver)
                    view.setBackgroundResource(R.drawable.shape_rect)
                    view.adapter =
                            ArrayAdapter<String>(
                                context,
                                android.R.layout.simple_dropdown_item_1line,
                                reportDao.options
                            )
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        130
                    )
                    params.setMargins(46, 20, 46, 0)
                    view.layoutParams = params
                    return view

                }
                Constants.MULTILINE -> {
                    val view = CustomEditText(context, valueObserver)
                    view.hint = reportDao.fieldName
                    view.setSingleLine(false)
                    view.setBackgroundResource(R.drawable.shape_rect)
                    view.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
                    view.gravity = Gravity.TOP
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350)
                    params.setMargins(46, 20, 46, 0)
                    view.layoutParams = params

                    return view
                }

                Constants.COMPOSITE -> {
                    val view = TextView(context)
                    view.text = reportDao.fieldName
                    view.setTypeface(null, Typeface.BOLD)
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350)
                    params.setMargins(46, 20, 46, 0)
                    view.layoutParams = params

                    return view
                }

            }

            return null
        }
    }
}