package com.assist.dg.androidassist.util;

import com.assist.dg.androidassist.fragment.BaseFragment;
import com.assist.dg.androidassist.fragment.CollegeListFragment;
import com.assist.dg.androidassist.fragment.UniversityListFragment;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public enum FragmentType {

  INIT(null),
  COLLEGE_LIST(CollegeListFragment.class),
  UNIVERSITY_LIST(UniversityListFragment.class);

  private Class<? extends BaseFragment> mFragmentClass;
  private String mTitle;

  FragmentType(Class<? extends BaseFragment> frag) {
    mFragmentClass = frag;
  }

  public Class<? extends BaseFragment> getFragmentClass() {
    return mFragmentClass;
  }

  public int getValue() {
    return ordinal();
  }
}

