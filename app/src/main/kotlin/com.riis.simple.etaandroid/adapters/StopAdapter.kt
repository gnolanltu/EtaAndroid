package com.riis.simple.etaandroid.adapters

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.databinding.StopListItemBinding
import com.riis.simple.etaandroid.viewmodel.StopViewModel

class StopAdapter(val stopList: List<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: StopListItemBinding
        val rowView: View
        if (convertView == null) {
            rowView = LayoutInflater.from(parent!!.context).inflate(R.layout.stop_list_item, parent, false)
            binding = DataBindingUtil.bind(rowView)
        } else {
            rowView = convertView
            binding = DataBindingUtil.getBinding<StopListItemBinding>(rowView)
        }

        binding.viewModel = StopViewModel(stopList[position])

        return rowView
    }

    override fun getItem(position: Int): Any {
        return stopList[position]
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

    override fun getCount(): Int {
        return stopList.count()
    }
}