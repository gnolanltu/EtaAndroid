package com.riis.simple.etaandroid.view.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.riis.simple.etaandroid.model.Route

class RoutesAdapter(val context: Context, val routeList: List<Route>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)

        var rowView = convertView
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.simple_list_item_1, parent, false)
        }

        (rowView as TextView).text = routeList[position].routeId

        return rowView
    }

    override fun getItem(position: Int): Any {
        return routeList[position]
    }

    override fun getItemId(position: Int): Long {
        return routeList[position].id!!
    }

    override fun getCount(): Int {
        return routeList.count()
    }
}
