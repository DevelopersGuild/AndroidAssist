package com.assist.dg.androidassist.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.adapter.CollegeListAdapter;
import com.assist.dg.androidassist.model.College;
import com.assist.dg.androidassist.service.ApiProvider;
import com.assist.dg.androidassist.util.WLog;
import java.util.List;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class CollegeListFragment extends BaseFragment
    implements CollegeListAdapter.OnCollegeItemClickListener {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.college_list) RecyclerView list;
  @Bind(R.id.college_navigation) NavigationView navigationView;
  @Bind(R.id.college_drawer) DrawerLayout drawerLayout;

  private CollegeListAdapter adapter;

  @Override protected int getLayoutId() {
    return R.layout.fragment_college_list;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getBaseActivity().setSupportActionBar(toolbar);

    final ActionBar ab = getBaseActivity().getSupportActionBar();
    if (ab != null) {
      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
      ab.setDisplayHomeAsUpEnabled(true);
    }

    setupDrawerContent(navigationView);

    adapter = new CollegeListAdapter();
    LinearLayoutManager m =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    list.setLayoutManager(m);
    adapter.setClickListener(this);

    new ApiProvider().getColleges(new Callback<List<College>>() {
      @Override public void onResponse(Response<List<College>> response, Retrofit retrofit) {
        WLog.i("status: " + response.code() + ", message: " + response.message());
        WLog.i("size: " + response.body().size());

        adapter.setColleges(response.body());

        new Handler(Looper.getMainLooper()).post(new Runnable() {
          @Override public void run() {
            list.setAdapter(adapter);
          }
        });
      }

      @Override public void onFailure(Throwable t) {
        t.printStackTrace();
      }
    });
  }

  @Override public void onClickCollegeItem(College c) {
    WLog.i("selected college: " + c.getName());
  }

  private void setupDrawerContent(final NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
          }
        });
  }
}
