package com.assist.dg.androidassist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.fragment.BaseFragment;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    try {
      getFragmentManager().beginTransaction()
          .add(R.id.activity_layout, createBaseFragment())
          .commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public abstract BaseFragment createBaseFragment();
}
