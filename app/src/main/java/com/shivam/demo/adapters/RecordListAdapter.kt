package com.shivam.demo.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.shivam.demo.R
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.ReportDao
import com.shivam.demo.utils.IValueObserver
import com.shivam.demo.utils.ViewFactory
import kotlinx.android.synthetic.main.itemview_recordlist.view.*

class RecordListAdapter(var context: Context, var list: List<ReportDao>) :
    RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.itemview_recordlist, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHeader.visibility = View.GONE
        holder.rvSubList.visibility = View.GONE
        holder.container.visibility = View.GONE

        if (list[position].type.equals(Constants.COMPOSITE, true)) {
            holder.tvHeader.visibility = View.VISIBLE
            holder.rvSubList.visibility = View.VISIBLE

            holder.tvHeader.text = list[position].fieldName
            holder.rvSubList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.rvSubList.adapter = RecordListAdapter(context, list[position].fields!!)
        } else {
            holder.container.visibility = View.VISIBLE
            holder.container.removeAllViews()
            holder.container.addView(
                ViewFactory.getView(context, list[position],
                    IValueObserver { value -> list[position].value = value }), 0
            )

        }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvHeader: TextView = itemView.tvHeader
        val rvSubList: RecyclerView = itemView.rvSubList
        val container: LinearLayout = itemView.itemview_container


    }
}