package com.shivam.demo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shivam.demo.R
import kotlinx.android.synthetic.main.itemview_reportlist.view.*

class ReportListAdapter(var list: JsonArray, var context: Context) :
    RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = inflater.inflate(R.layout.itemview_reportlist, p0, false)

        return ViewHolder((v))
    }

    override fun getItemCount(): Int {
        return list.size()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position] as JsonObject
        val city = item.get("City")
        holder.name.text = item.get("name").asString + (if (item.get("age") != null) " - " + item.get("age") else "")

        holder.address.text = if (city!=null) ("City: " + item.get("City")) else ""


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tvName
        var address: TextView = itemView.tvAddress
    }
}