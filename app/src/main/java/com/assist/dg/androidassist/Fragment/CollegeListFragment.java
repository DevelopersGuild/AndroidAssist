package com.assist.dg.androidassist.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.adapter.CollegeListAdapter;
import com.assist.dg.androidassist.adapter.ListAdapter;
import com.assist.dg.androidassist.model.College;
import com.assist.dg.androidassist.util.ActivityManager;
import com.assist.dg.androidassist.util.BundleSet;
import com.assist.dg.androidassist.util.FragmentType;
import com.assist.dg.androidassist.util.WLog;
import java.util.List;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class CollegeListFragment extends ListFragment<College>
    implements ListAdapter.OnItemClickListener<College> {

  @Override protected ListAdapter<College> createListAdapter() {
    return new CollegeListAdapter(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_list;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getApiProvider().getColleges(this);
  }

  @Override public void onResponse(final Response<List<College>> response, Retrofit retrofit) {
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

  @Override public void onClickItem(College c) {
    WLog.i("selected college: " + c.getName());
    ActivityManager.with(getBaseActivity(), FragmentType.UNIVERSITY_LIST)
        .addBundle(new BundleSet.Builder().putCollegeCode(c.getCode()).build().getBundle())
        .go();
  }
}
