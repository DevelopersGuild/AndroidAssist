package com.assist.dg.androidassist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by doyonghoon on 2015. 10. 28..
 */
public class University extends AssistData {

  @SerializedName("name") private String name;
  @SerializedName("value") private String code;

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
