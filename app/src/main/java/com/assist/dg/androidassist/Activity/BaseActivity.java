package com.assist.dg.androidassist.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.fragment.BaseFragment;
import com.assist.dg.androidassist.util.ActivityManager;
import com.assist.dg.androidassist.util.FragmentType;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class BaseActivity extends AppCompatActivity {

  @Bind(R.id.base_navigation) NavigationView navigationView;
  @Bind(R.id.base_drawer) DrawerLayout drawerLayout;
  @Bind(R.id.toolbar) Toolbar toolbar;

  private Bundle bundle;
  protected BaseFragment mFragment;
  private FragmentType fragmentType;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    if (getIntent() != null) {
      if (getIntent().hasExtra(ActivityManager.TASK)) {
        fragmentType = (FragmentType) getIntent().getSerializableExtra(ActivityManager.TASK);
      }

      if (getIntent().hasExtra(ActivityManager.BUNDLE)) {
        bundle = getIntent().getBundleExtra(ActivityManager.BUNDLE);
        //mAnimType = (ActivityStarter.AnimationType) bundle.getSerializable(
        //    BundleSet.TRANSITION_ANIMATION_TYPE);
      }
    }

    if (fragmentType == null) {
      fragmentType = FragmentType.COLLEGE_LIST;
    }

    if (savedInstanceState == null) {
      try {
        mFragment = fragmentType.getFragmentClass().newInstance();
        if (bundle != null) {
          mFragment.setArguments(bundle);
        }

        //setOnBackKeyListener(mFragment.getOnBackKeyListener());
      } catch (Exception localException) {
        localException.printStackTrace();
      }

      try {
        getFragmentManager().beginTransaction()
            .add(R.id.activity_layout, mFragment)
            .commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    final ActionBar ab = getSupportActionBar();
    if (ab != null) {
      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
      ab.setDisplayHomeAsUpEnabled(true);
    }
    setupDrawerContent(navigationView);
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

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(navigationView);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    fragmentType = null;
    ButterKnife.unbind(this);
  }

  public FragmentType getFragmentType() {
    return fragmentType;
  }
}
