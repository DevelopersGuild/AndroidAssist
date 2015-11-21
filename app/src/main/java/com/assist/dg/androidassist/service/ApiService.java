package com.assist.dg.androidassist.service;

import com.assist.dg.androidassist.model.AssistData;
import com.assist.dg.androidassist.model.College;
import com.assist.dg.androidassist.model.Course;
import com.assist.dg.androidassist.model.Major;
import com.assist.dg.androidassist.model.University;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public interface ApiService<T extends AssistData> {
  @GET("/api/ias") Call<List<College>> colleges();

  @GET("/api/{college}/oias") Call<List<University>> universities(@Path("college") String college);

  @GET("/api/{college}/{university}/dora") Call<List<Major>> majors(@Path("college") String college, @Path("university") String university);

  @GET("/api/{college}/{university}/{major}/courses") Call<List<Course>> courses(
      @Path("college") String college, @Path("university") String university,
      @Path("major") String major);
}
