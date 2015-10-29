package com.assist.dg.androidassist.activity;

import com.assist.dg.androidassist.fragment.BaseFragment;
import com.assist.dg.androidassist.fragment.CollegeListFragment;

public class MainActivity extends BaseActivity {

  @Override public BaseFragment createBaseFragment() {
    return new CollegeListFragment();
  }
}
