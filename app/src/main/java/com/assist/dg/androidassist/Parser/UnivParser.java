package com.assist.dg.androidassist.Parser;

import android.os.AsyncTask;
import android.util.Log;

import com.assist.dg.androidassist.Fragment.CollegeUnivFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hendryjoe on 10/12/15.
 */
public class UnivParser extends AsyncTask<ArrayList<String>, Void, String> {

    ArrayList<String> listOfUniversities;
    ArrayList<String> listOfUnivValues;

    @Override
    protected String doInBackground(ArrayList<String>... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String univJsonStr = null;

        listOfUniversities = new ArrayList<String>();
        listOfUniversities = params[0];
        listOfUnivValues = params[1];

        try {
            final String API_URL = "http://159.203.90.30:8081/api/schools";

            URL url = new URL(API_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return null;
            }
            
            univJsonStr = buffer.toString();
        } catch (Exception e) {
            Log.e("univ_parser", "ERROR CONNECTING TO API", e);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (Exception e){
                    Log.e("univ_parser", "ERROR CLOSING", e);
                }
            }
        }
        /**
        //Testing the JSON, using hardcoded data
        univJsonStr = "[" + "{" + "\"" + "school" + "\"" + ":\"" + "California Maritime Academy" +"\"" + ",\"" + "value\"" + ":\"CSUMA\"}," +
        "{\"school\":\"California Polytechnic University, Pomona\",\"value\":\"CPP\"}," +
        "{\"school\":\"California Polytechnic University, San Luis Obispo\",\"value\":\"CPSLO\"}" + "]";
        **/
        return univJsonStr;
    }

    @Override
    protected void onPostExecute(String json){
        try {
            setListOfUniversities(json);
        } catch (Exception e) {
            Log.e("univ_parser", "Error converting json to list", e);
        }
    }

    public void setListOfUniversities(String univJsonStr) throws JSONException{
        JSONArray arrayOfUnivs = new JSONArray(univJsonStr);
        JSONObject univs;
        listOfUniversities.clear();
        listOfUnivValues.clear();
        listOfUniversities.add("Choose a University");
        listOfUnivValues.add("default_value");
        for (int i=0; i<arrayOfUnivs.length(); i++){
            univs = arrayOfUnivs.getJSONObject(i);
            listOfUniversities.add(univs.getString("school"));
            listOfUnivValues.add(univs.getString("value"));
        }
    }
}


