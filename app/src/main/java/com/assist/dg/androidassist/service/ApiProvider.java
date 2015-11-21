package com.assist.dg.androidassist.service;

import android.support.annotation.NonNull;
import com.assist.dg.androidassist.App;
import com.assist.dg.androidassist.model.AssistData;
import com.assist.dg.androidassist.model.College;
import com.assist.dg.androidassist.model.Major;
import com.assist.dg.androidassist.model.University;
import com.assist.dg.androidassist.util.WLog;
import java.util.List;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class ApiProvider<T extends AssistData> {

  private final Retrofit retrofit;
  private final ApiService apiService;

  public ApiProvider() {
    retrofit = App.getRetrofit();
    apiService = retrofit.create(ApiService.class);
  }

  public void getColleges(Callback<List<College>> callback) {
    apiService.colleges().enqueue(callback);
  }

  public void getUniversities(@NonNull String code, Callback<List<University>> callback) {
    apiService.universities(code).enqueue(callback);
  }

  public void getMajors(@NonNull String collegeCode, @NonNull String universityCode,
      Callback<List<Major>> callback) {
    WLog.i("college: " + collegeCode + ", university: " + universityCode);
    apiService.majors(collegeCode, universityCode).enqueue(callback);
  }
}
