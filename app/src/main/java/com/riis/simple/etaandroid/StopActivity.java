package com.riis.simple.etaandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class StopActivity extends AppCompatActivity {

    public static final String EXTRA_COMPANY = "company";
    public static final String EXTRA_ROUTEID = "routeId";
    public static final String EXTRA_DIRECTION = "direction1";
    public static final String EXTRA_DAYSACTIVE = "daysActive";

    private String routeId;
    private String currentDirection;
    private String direction1;
    private String direction2;
    private String daysActive;
    private String jsonString;

    private ArrayList<String> stopsArrayList;
    private ListView stopsListView;
    private int companyNumber;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.riis.simple.etaandroid.R.layout.activity_stop);

        Intent intent = getIntent();
        companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1);
        routeId = intent.getStringExtra(EXTRA_ROUTEID);
        direction1 = intent.getStringExtra(EXTRA_DIRECTION);
        daysActive = intent.getStringExtra(EXTRA_DAYSACTIVE);
        stopsListView = (ListView) findViewById(com.riis.simple.etaandroid.R.id.stops);
        context = this;

        //if daysActive has more than one value, grab first value
        int commaIndex = daysActive.indexOf(",");
        if (commaIndex != -1) {
            daysActive = daysActive.substring(0, commaIndex);
        }

        //Always show North or Eastbound first for consistency
        switch (direction1.toLowerCase()) {
            case "northbound":
                direction1 = "Northbound";
                direction2 = "Southbound";
                break;
            case "southbound":
                direction1 = "Northbound";
                direction2 = "Southbound";
                break;
            case "eastbound":
                direction1 = "Eastbound";
                direction2 = "Westbound";
                break;
            case "westbound":
                direction1 = "Eastbound";
                direction2 = "Westbound";
                break;
            default:
                break;
        }

        ToggleButton directionButton;
        directionButton = (ToggleButton) findViewById(com.riis.simple.etaandroid.R.id.directionButton);
        directionButton.setTextOff(direction1);
        directionButton.setTextOn(direction2);
        directionButton.setChecked(false);
        directionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currentDirection = direction2;
                } else {
                    currentDirection = direction1;
                }
                new AsyncJsonCaller().execute();
            }
        });

        currentDirection = direction1;

        new AsyncJsonCaller().execute();
    }

    private class AsyncJsonCaller extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            JsonFetcher jsonFetcher = new JsonFetcher();
            UrlStringBuilder urlStringBuilder = new UrlStringBuilder();
            try {
                jsonString = jsonFetcher.fetchUrl(urlStringBuilder.getStopsUrl(companyNumber, routeId,
                        currentDirection, daysActive));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            JsonParser jsonParser = new JsonParser();
            try {
                stopsArrayList = jsonParser.getStops(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stopsListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, stopsArrayList));
        }
    }
}