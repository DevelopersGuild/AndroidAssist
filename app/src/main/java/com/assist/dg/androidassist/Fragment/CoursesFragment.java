package com.assist.dg.androidassist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.assist.dg.androidassist.activity.InfoActivity;
import com.assist.dg.androidassist.adapter.CourseListAdapter;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.RequiredClass;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.util.ArrayList;

/**
 * Created by hendryjoe on 10/12/15.
 */

public class CoursesFragment extends Fragment {

  RequiredClass[] temp;
  ArrayList<RequiredClass> classList;
  SwipeListView swipeListView;

  public CoursesFragment() {

    classList = new ArrayList<RequiredClass>();
    //Fill with dummy data
    int numOfPrerequisites = 3;
    int numOfRequiredClasses = 20;
    temp = new RequiredClass[numOfRequiredClasses];

    for (int i = 0; i < numOfRequiredClasses; i++) {
      RequiredClass[] reqArray = new RequiredClass[numOfPrerequisites];
      for (int j = 0; j < numOfPrerequisites; j++) {
        reqArray[j] = new RequiredClass();
      }

      temp[i] = new RequiredClass();
      temp[i].setFormalClassName("CLASS " + i);
      temp[i].setPrerequisites(reqArray);

      classList.add(temp[i]);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

    swipeListView = (SwipeListView) rootView.findViewById(R.id.class_list);
    CourseListAdapter courseListAdapter = new CourseListAdapter(rootView.getContext(), classList);

    swipeListView.setAdapter(courseListAdapter);

    View view = inflater.inflate(R.layout.swipelistview, null, false);
    Button moreInfoButton = (Button) view.findViewById(R.id.more_info_button);

    moreInfoButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        System.out.println("LISTENING");
        Log.e("tag", "CLICKING THE BUTTON");

        Intent intent = new Intent(getActivity(), InfoActivity.class);
        startActivity(intent);
      }
    });

    return rootView;
  }
}