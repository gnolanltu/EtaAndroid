package com.riis.simple.etaandroid.adapters

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.databinding.CompanyListItemBinding
import com.riis.simple.etaandroid.model.Company
import com.riis.simple.etaandroid.viewmodel.CompanyViewModel

class CompanyAdapter (val companyList: List<Company>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: CompanyListItemBinding
        val rowView: View
        if (convertView == null) {
            rowView = LayoutInflater.from(parent!!.context).inflate(R.layout.company_list_item, parent, false)
            binding = DataBindingUtil.bind(rowView)
        } else {
            rowView = convertView
            binding = DataBindingUtil.getBinding<CompanyListItemBinding>(rowView)
        }

        binding.viewModel = CompanyViewModel(parent!!.context, companyList[position])

        return rowView
    }

    override fun getItem(position: Int): Any {
        return companyList[position]
    }

    override fun getItemId(position: Int): Long {
        return companyList[position].id.toLong()
    }

    override fun getCount(): Int {
        return companyList.count()
    }
}