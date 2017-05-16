package com.riis.simple.etaandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public ArrayList<String> parseRoutes(String jsonString) throws JSONException {
        //Create jsonArray for routeIDs
        final JSONArray routeID = new JSONArray(jsonString);

        //Get number of routes
        final int n = routeID.length();

        //for each route enter it into an array
        ArrayList<String> returnArray = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            final JSONObject instance = routeID.getJSONObject(i);
            returnArray.add(instance.getString("routeID"));
        }
        return returnArray;
    }

    public String getDirection(String jsonString, int index) throws JSONException {
        //Create jsonArray for routeIDs
        final JSONArray directions = new JSONArray(jsonString);

        //Get number of routes
        final int n = directions.length();

        //for each route enter it into an array
        ArrayList<String> returnArray = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            final JSONObject instance = directions.getJSONObject(i);
            returnArray.add(instance.getString("direction1"));
        }
        return returnArray.get(index);
    }

    public String getRouteId(String jsonString, int index) throws JSONException {
        //Create jsonArray for routeIDs
        final JSONArray routeID = new JSONArray(jsonString);

        //Get number of routes
        final int n = routeID.length();

        //for each route enter it into an array
        ArrayList<String> returnArray = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            final JSONObject instance = routeID.getJSONObject(i);
            int temp = instance.getInt("id");
            returnArray.add(Integer.toString(temp));
        }
        return returnArray.get(index);
    }

    public String getDaysActive(String jsonString, int index) throws JSONException {
        //Create jsonArray for routeIDs
        final JSONArray daysActive = new JSONArray(jsonString);

        //Get number of routes
        final int n = daysActive.length();

        //for each route enter it into an array
        ArrayList<String> returnArray = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            final JSONObject instance = daysActive.getJSONObject(i);
            returnArray.add(instance.getString("daysActive"));
        }
        return returnArray.get(index);
    }

    public ArrayList<String> getStops(String jsonString) throws JSONException {
        //Create jsonArray for routeIDs
        final JSONArray stops = new JSONArray(jsonString);

        //Get number of stops
        final int n = stops.length();

        //for each route enter it into an array
        ArrayList<String> returnArray = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            final JSONObject instance = stops.getJSONObject(i);
            returnArray.add(instance.getString("stopName"));
        }
        return returnArray;
    }
}