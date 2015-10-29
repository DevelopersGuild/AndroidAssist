package com.assist.dg.androidassist.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.assist.dg.androidassist.fragment.ChooseMajorFragment;
import com.assist.dg.androidassist.fragment.CollegeUnivFragment;
import com.assist.dg.androidassist.R;

public class MainActivity extends ActionBarActivity {

  private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
  String selectedUnivValue;

  public void setSelectedUnivValue(String univValue) {
    selectedUnivValue = univValue;
  }

  public String getSelectedUnivValue() {
    return selectedUnivValue;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new CollegeUnivFragment())
          .commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  public void showMajorFragment() {
    // Create new fragment and transaction

    Fragment newFragment = new ChooseMajorFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

    transaction.replace(R.id.container, newFragment, TAG_FRAGMENT);
    transaction.addToBackStack(null);

    transaction.commit();
  }

  @Override public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 0) {
      getFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
