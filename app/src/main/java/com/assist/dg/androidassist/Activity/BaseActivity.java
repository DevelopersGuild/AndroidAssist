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

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Bind(R.id.base_navigation) NavigationView navigationView;
  @Bind(R.id.base_drawer) DrawerLayout drawerLayout;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    try {
      getFragmentManager().beginTransaction()
          .add(R.id.activity_layout, createBaseFragment())
          .commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    final ActionBar ab = getSupportActionBar();
    if (ab != null) {
      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
      ab.setDisplayHomeAsUpEnabled(true);
    }
    setupDrawerContent(navigationView);
  }

  public abstract BaseFragment createBaseFragment();

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
}
