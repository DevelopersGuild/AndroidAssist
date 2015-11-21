package com.assist.dg.androidassist.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.assist.dg.androidassist.adapter.ListAdapter;
import com.assist.dg.androidassist.adapter.MajorListAdapter;
import com.assist.dg.androidassist.model.Major;
import com.assist.dg.androidassist.util.BundleSet;
import com.assist.dg.androidassist.util.WLog;
import java.util.List;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class MajorListFragment extends ListFragment<Major> {

  @Override protected ListAdapter<Major> createListAdapter() {
    return new MajorListAdapter(this);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final Bundle b = getArguments();
    getApiProvider().getMajors(b.getString(BundleSet.COLLEGE_CODE),
        b.getString(BundleSet.UNIVERSITY_CODE), this);
  }

  @Override public void onResponse(final Response<List<Major>> response, Retrofit retrofit) {
    Handler handler = new Handler(Looper.getMainLooper());
    handler.post(new Runnable() {
      @Override public void run() {
        getAdapter().setData(response.body());
      }
    });
  }

  @Override public void onFailure(Throwable t) {
    t.printStackTrace();
  }

  @Override public void onClickItem(Major c) {
    WLog.i("major: " + c.getName());
  }
}
