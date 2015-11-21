package com.assist.dg.androidassist.util;

import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class BundleSet {

  public static final String COLLEGE_CODE = "CollegeCode";
  public static final String UNIVERSITY_CODE = "UniversityCode";
  public static final String TRANSITION_ANIMATION_TYPE = "TransitionAnimationType";

  private Bundle mBundle;
  private Builder mBuilder = null;

  private BundleSet(Builder builder) {
    this.mBuilder = builder;
    mBundle = new Bundle();
    if (!TextUtils.isEmpty(mBuilder.collegeCode)) {
      mBundle.putString(COLLEGE_CODE, mBuilder.collegeCode);
    }
    if (!TextUtils.isEmpty(mBuilder.universityCode)) {
      mBundle.putString(UNIVERSITY_CODE, mBuilder.universityCode);
    }
  }

  public Builder getBuilder() {
    return this.mBuilder;
  }

  public Bundle getBundle() {
    return mBundle;
  }

  public static class Builder {

    private String collegeCode = null;
    private String universityCode = null;

    public Builder() {
    }

    public Builder putCollegeCode(String c) {
      collegeCode = c;
      return this;
    }

    public Builder putUniversityCode(String c) {
      universityCode = c;
      return this;
    }


    public BundleSet build() {
      return new BundleSet(this);
    }
  }
}
