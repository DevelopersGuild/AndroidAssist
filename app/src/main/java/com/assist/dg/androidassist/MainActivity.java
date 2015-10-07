package com.assist.dg.androidassist;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

import static android.widget.AdapterView.*;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment{

        ArrayList<String> listOfColleges;
        ArrayList<String> listOfUniversities;
        Spinner fromCollegeSpinner;
        Spinner toUniversitySpinner;

        public PlaceholderFragment() {
            //Populate the colleges and univs list with dummy data
            String dummy;
            listOfColleges = new ArrayList<String>();
            listOfUniversities = new ArrayList<String>();

            listOfColleges.add("Choose a Community College");
            for (int i=0; i<15; i++){
                dummy = "Community College " + i;
                listOfColleges.add(dummy);
            }

            listOfUniversities.add("Choose a University");
            for (int i=0; i<12; i++){
                dummy = "University" + i;
                listOfUniversities.add(dummy);
            }


        }

        class SpinnerItemSelectedListener implements OnItemSelectedListener{

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;

                if(spinner.getId() == R.id.from_college_spinner)
                {
                    if (position != 0){
                        toUniversitySpinner.setVisibility(View.VISIBLE);
                    }
                }
                else if(spinner.getId() == R.id.to_university_spinner)
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            SpinnerItemSelectedListener listener = new SpinnerItemSelectedListener();

            fromCollegeSpinner = (Spinner) rootView.findViewById(R.id.from_college_spinner);
            ArrayAdapter<String> collegeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfColleges);
            fromCollegeSpinner.setAdapter(collegeAdapter);

            toUniversitySpinner = (Spinner) rootView.findViewById(R.id.to_university_spinner);
            ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfUniversities);
            toUniversitySpinner.setAdapter(universityAdapter);

            fromCollegeSpinner.setOnItemSelectedListener(listener);
            toUniversitySpinner.setOnItemSelectedListener(listener);


            return rootView;
        }

    }
}
