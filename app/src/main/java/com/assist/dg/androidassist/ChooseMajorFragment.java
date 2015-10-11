package com.assist.dg.androidassist;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by hendryjoe on 10/7/15.
 */
public class ChooseMajorFragment extends Fragment {

    ArrayList<String> listOfMajors;
    Spinner chooseMajorSpinner;
    Button viewCoursesButton;

    public ChooseMajorFragment() {
        listOfMajors = new ArrayList<String>();
        listOfMajors.add("Choose a Major");

        //Add dummy data
        for (int i=0; i<15; i++){
            listOfMajors.add("Major " + i);
        }
    }

    class MajorSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_major, container, false);

        chooseMajorSpinner = (Spinner) rootView.findViewById(R.id.choose_major_spinner);
        ArrayAdapter<String> majorAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listOfMajors);
        chooseMajorSpinner.setAdapter(majorAdapter);

        chooseMajorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewCoursesButton = (Button) rootView.findViewById(R.id.view_courses_button);
        viewCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoursesActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

}