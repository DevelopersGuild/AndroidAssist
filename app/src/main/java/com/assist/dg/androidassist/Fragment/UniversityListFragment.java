package com.assist.dg.androidassist.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.assist.dg.androidassist.adapter.ListAdapter;
import com.assist.dg.androidassist.adapter.UniversityListAdapter;
import com.assist.dg.androidassist.model.University;
import com.assist.dg.androidassist.util.ActivityManager;
import com.assist.dg.androidassist.util.BundleSet;
import com.assist.dg.androidassist.util.FragmentType;
import com.assist.dg.androidassist.util.WLog;
import java.util.List;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 10. 30..
 */
public class UniversityListFragment extends ListFragment<University>
    implements ListAdapter.OnItemClickListener<University> {

  private String collegeCode;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final Bundle b = getArguments();
    if (b.containsKey(BundleSet.COLLEGE_CODE)) {
      WLog.i("code: " + b.getString(BundleSet.COLLEGE_CODE));
      getApiProvider().getUniversities(collegeCode = b.getString(BundleSet.COLLEGE_CODE), this);
    }
  }

  @Override protected ListAdapter<University> createListAdapter() {
    return new UniversityListAdapter(this);
  }

  @Override public void onResponse(final Response<List<University>> response, Retrofit retrofit) {
    WLog.i("res: " + response.body().size());
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

  @Override public void onClickItem(University c) {
    WLog.i("selected college: " + c.getName());
    ActivityManager.with(getBaseActivity(), FragmentType.MAJOR_LIST)
        .addBundle(new BundleSet.Builder().putCollegeCode(collegeCode)
            .putUniversityCode(c.getCode())
            .build()
            .getBundle())
        .go();
  }
}
