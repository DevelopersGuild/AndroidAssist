package com.assist.dg.androidassist.fragment;

/**
 * Created by hendryjoe on 10/7/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.parser.UnivParser;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class CollegeUnivFragment extends Fragment {

  ArrayList<String> listOfColleges;
  ArrayList<String> listOfUniversities;
  ArrayList<String> listOfUnivValues;
  Spinner fromCollegeSpinner;
  Spinner toUniversitySpinner;

  UnivParser univParser;
  public String selectedUnivValue;

  public CollegeUnivFragment() {

    //Populate the colleges and univs list with dummy data
    String dummy;
    listOfColleges = new ArrayList<String>();
    listOfUniversities = new ArrayList<String>();
    listOfUnivValues = new ArrayList<String>();

    listOfColleges.add("Choose a Community College");
    for (int i = 0; i < 3; i++) {
      dummy = "Community College " + i;
      listOfColleges.add(dummy);
    }

    listOfUniversities.add("Choose a University");
    for (int i = 0; i < 3; i++) {
      dummy = "University" + i;
      listOfUniversities.add(dummy);
    }

    //Use real data, if it works, delete above code
    univParser = new UnivParser();
    univParser.execute(listOfUniversities, listOfUnivValues);
  }

  class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      Spinner spinner = (Spinner) parent;

      if (spinner.getId() == R.id.from_college_spinner) {
        if (position != 0) {
          toUniversitySpinner.setVisibility(View.VISIBLE);
        }
      } else if (spinner.getId() == R.id.to_university_spinner) {
        if (position != 0) {
          selectedUnivValue = listOfUnivValues.get(position);
          System.out.println(selectedUnivValue);
          //((MainActivity) getActivity()).setSelectedUnivValue(selectedUnivValue);
          //((MainActivity) getActivity()).showMajorFragment();
        }
      }
    }

    @Override public void onNothingSelected(AdapterView<?> parent) {

    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    SpinnerItemSelectedListener listener = new SpinnerItemSelectedListener();

    fromCollegeSpinner = (Spinner) rootView.findViewById(R.id.from_college_spinner);
    ArrayAdapter<String> collegeAdapter =
        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
            listOfColleges);
    fromCollegeSpinner.setAdapter(collegeAdapter);

    toUniversitySpinner = (Spinner) rootView.findViewById(R.id.to_university_spinner);
    ArrayAdapter<String> universityAdapter =
        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
            listOfUniversities);
    toUniversitySpinner.setAdapter(universityAdapter);

    fromCollegeSpinner.setOnItemSelectedListener(listener);
    toUniversitySpinner.setOnItemSelectedListener(listener);

    return rootView;
  }
}