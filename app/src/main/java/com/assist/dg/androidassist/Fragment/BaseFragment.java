package com.assist.dg.androidassist.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.assist.dg.androidassist.activity.BaseActivity;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public abstract class BaseFragment extends Fragment {

  @LayoutRes protected abstract int getLayoutId();

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = null;
    throwLayoutIdError();
    if (getLayoutId() > 0) {
      view = inflater.inflate(getLayoutId(), container, false);
    }
    ButterKnife.bind(this, view);
    return view;
  }

  public BaseActivity getBaseActivity() {
    return (BaseActivity) getActivity();
  }

  private void throwLayoutIdError() {
    if (getLayoutId() == 0x00) {
      throw new NullPointerException(
          "layoutId may not be 0 from " + ((Object) this).getClass().getSimpleName());
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
