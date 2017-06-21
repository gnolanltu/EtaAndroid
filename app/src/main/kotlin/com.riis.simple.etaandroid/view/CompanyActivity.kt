package com.riis.simple.etaandroid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.adapters.CompanyAdapter
import com.riis.simple.etaandroid.model.Company

class CompanyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        val busNamesArray = resources.getStringArray(R.array.busnames)
        val companyList = ArrayList<Company>()

        var index = 1
        for (name in busNamesArray) {
            val company = Company()
            company.name = name
            company.id = index++
            companyList.add(company)
        }

        val busListView: ListView = findViewById(R.id.busses) as ListView
        busListView.adapter = CompanyAdapter(companyList)
    }
}
