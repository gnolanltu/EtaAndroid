package com.riis.simple.etaandroid.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.riis.simple.etaandroid.R

class CompanyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        val busListView: ListView = findViewById(com.riis.simple.etaandroid.R.id.busses) as ListView
        val busNamesArray = resources.getStringArray(R.array.busnames)
        busListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busNamesArray)

        //Go to RouteActivity and pass company
        busListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@CompanyActivity, RouteActivity::class.java)
            intent.putExtra(RouteActivity.EXTRA_COMPANY, position + 1)
            startActivity(intent)
        }
    }
}
