package com.assist.dg.androidassist.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.adapter.ListAdapter;
import com.assist.dg.androidassist.model.AssistData;
import com.assist.dg.androidassist.service.ApiProvider;
import java.util.List;
import retrofit.Callback;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public abstract class ListFragment<T extends AssistData> extends BaseFragment
    implements ListAdapter.OnItemClickListener<T>, Callback<List<T>> {

  @Bind(R.id.college_list) RecyclerView list;
  private ListAdapter<T> adapter;
  private ApiProvider<T> apiProvider;

  protected abstract ListAdapter<T> createListAdapter();

  protected ApiProvider<T> getApiProvider() {
    return apiProvider;
  }

  protected ListAdapter<T> getAdapter() {
    return adapter;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_list;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    adapter = createListAdapter();
    apiProvider = new ApiProvider<T>();
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    list.setAdapter(adapter);
    list.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
  }

  //protected void loadData(Callback<List<T>> callback) {
  //  Bundle b = getArguments();
  //  switch (getBaseActivity().getFragmentType()) {
  //    case COLLEGE_LIST:
  //      getApiProvider().getColleges(callback);
  //      break;
  //    case UNIVERSITY_LIST:
  //      getApiProvider().getUniversities(b.getString(BundleSet.COLLEGE_CODE), callback);
  //      break;
  //    case MAJOR_LIST:
  //      getApiProvider().getMajors(b.getString(BundleSet.COLLEGE_CODE), BundleSet.UNIVERSITY_CODE,
  //          callback);
  //      break;
  //  }
  //}
}
