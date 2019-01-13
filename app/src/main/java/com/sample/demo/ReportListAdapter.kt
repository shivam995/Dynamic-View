package com.sample.demo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.demo.dao.Record
import kotlinx.android.synthetic.main.itemview_reportlist.view.*

class ReportListAdapter(var list: List<Record>, var context: Context) :
    RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var v = inflater.inflate(R.layout.itemview_reportlist, p0, false)

        return ViewHolder((v))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.name.text = item.name + (if (item.age != null) " - " + item.age else "")
        holder.address.text = if (!TextUtils.isEmpty(item.address)) ("Address: " + item.address) else ""


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tvName
        var address: TextView = itemView.tvAddress
    }
}