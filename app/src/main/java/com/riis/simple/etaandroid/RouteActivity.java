package com.riis.simple.etaandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    public static final String EXTRA_COMPANY = "company";

    private ListView routeList;

    private String routeId;
    private String direction;
    private String daysActive;
    private String jsonString;

    private int companyNumber;

    private ArrayList<String> routeNumbersArray;

    private JsonParser jsonParser;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.riis.simple.etaandroid.R.layout.activity_route);
        Intent intent = getIntent();
        companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1);
        jsonParser = new JsonParser();
        context = this;

        routeList = (ListView) findViewById(com.riis.simple.etaandroid.R.id.routes);

        routeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    routeId = jsonParser.getRouteId(jsonString, position);
                    direction = jsonParser.getDirection(jsonString, position);
                    daysActive = jsonParser.getDaysActive(jsonString, position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(RouteActivity.this, StopActivity.class);
                intent.putExtra(StopActivity.EXTRA_COMPANY, companyNumber);
                intent.putExtra(StopActivity.EXTRA_ROUTEID, routeId);
                intent.putExtra(StopActivity.EXTRA_DIRECTION, direction);
                intent.putExtra(StopActivity.EXTRA_DAYSACTIVE, daysActive);
                startActivity(intent);
            }
        });

        new AsyncCaller().execute();
    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            JsonFetcher jsonFetcher = new JsonFetcher();
            UrlStringBuilder urlStringBuilder = new UrlStringBuilder();
            try {
                jsonString = jsonFetcher.fetchUrl(urlStringBuilder.getRoutesUrl(companyNumber));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                routeNumbersArray = jsonParser.parseRoutes(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            routeList.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, routeNumbersArray));
        }
    }
}
