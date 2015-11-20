package com.assist.dg.androidassist.util;

import android.os.Bundle;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class BundleSet {

  public static final String COLLEGE_CODE = "CollegeCode";
  public static final String TRANSITION_ANIMATION_TYPE = "TransitionAnimationType";

  private Bundle mBundle;
  private Builder mBuilder = null;

  private BundleSet(Builder builder) {
    this.mBuilder = builder;
    mBundle = new Bundle();
    mBundle.putString(COLLEGE_CODE, mBuilder.collegeCode);
  }

  public Builder getBuilder() {
    return this.mBuilder;
  }

  public Bundle getBundle() {
    return mBundle;
  }

  public static class Builder {

    private String collegeCode = null;

    public Builder() {
    }

    //public Builder from(BundleSet.Builder builder) {
    //  return this;
    //}

    public Builder putCollegeCode(String c) {
      collegeCode = c;
      return this;
    }

    public BundleSet build() {
      return new BundleSet(this);
    }
  }
}
