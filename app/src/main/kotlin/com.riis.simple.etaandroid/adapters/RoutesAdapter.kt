package com.riis.simple.etaandroid.adapters

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.databinding.RouteListItemBinding
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.viewmodel.RouteViewModel

class RoutesAdapter(val routeList: List<Route>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: RouteListItemBinding
        val rowView: View
        if (convertView == null) {
            rowView = LayoutInflater.from(parent!!.context).inflate(R.layout.route_list_item, parent, false)
            binding = DataBindingUtil.bind(rowView)
        } else {
            rowView = convertView
            binding = DataBindingUtil.getBinding<RouteListItemBinding>(rowView)
        }

        binding.viewModel = RouteViewModel(parent!!.context, routeList[position])

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