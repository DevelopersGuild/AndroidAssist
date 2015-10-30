package com.assist.dg.androidassist.service;

import com.assist.dg.androidassist.App;
import com.assist.dg.androidassist.model.College;
import java.util.List;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class ApiProvider {

  private final Retrofit retrofit;
  private final ApiService apiService;

  public ApiProvider() {
    retrofit = App.getRetrofit();
    apiService = retrofit.create(ApiService.class);
  }

  public void getColleges(Callback<List<College>> callback) {
    apiService.colleges().enqueue(callback);
  }
}
