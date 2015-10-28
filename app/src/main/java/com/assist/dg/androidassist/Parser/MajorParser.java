package com.assist.dg.androidassist.Parser;

import android.os.AsyncTask;
import android.util.Log;

import com.assist.dg.androidassist.Fragment.ChooseMajorFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hendryjoe on 10/18/15.
 */
public class MajorParser extends AsyncTask<Void, Void, String> {

    ArrayList<String> listOfMajors;
    ArrayList<String> listOfMajorValues;
    String selectedUnivValue;

    ChooseMajorFragment majorFragment;

    public MajorParser(ChooseMajorFragment majorFragment){
        this.majorFragment = majorFragment;
        this.listOfMajors = majorFragment.listOfMajors;
        this.listOfMajorValues = majorFragment.listOfMajorValue;
        this.selectedUnivValue = majorFragment.selectedUnivValue;
    }


    @Override
    protected String doInBackground(Void... params) {

        String majorJsonStr;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String API_URL = "http://159.203.90.30:8081/api/" + selectedUnivValue + "/majors";
        //Testing
        Log.d("major_parser", API_URL);

        try {
            URL url = new URL(API_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read the input
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while(reader.readLine() != null){
                line = reader.readLine();
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return null;
            }

            majorJsonStr = buffer.toString();
        } catch (Exception e){
            Log.e("major_parser", "ERROR ON CONNECTING TO PARSE MAJOR");
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (Exception e){
                    Log.e("major_parser", "ERROR CLOSING STREAM");
                }
            }
        }
        return majorJsonStr;
    }

    @Override
    protected void onPostExecute(String majorJsonStr){
        setListOfMajors(majorJsonStr);
    }

    public void setListOfMajors(String majorJsonStr){
        try {
            JSONArray arrayOfMajors = new JSONArray(majorJsonStr);
            JSONObject major;
            listOfMajors.clear();
            listOfMajorValues.clear();
            listOfMajors.add("Choose a Major");
            listOfMajorValues.add("default_value");
            for (int i=0; i<arrayOfMajors.length(); i++){
                major = arrayOfMajors.getJSONObject(i);
                listOfMajors.add(major.getString("major"));
                listOfMajorValues.add(major.getString("value"));
            }
        } catch (Exception e){
            System.out.println(majorJsonStr);
            Log.e("major_parser", "ERROR CONVERTING JSON INTO MAJOR LIST");
        }
    }
}
