package com.github.satoshun.android.mypractice.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.satoshun.android.mypractice.BaseActivity;
import com.github.satoshun.android.mypractice.R;
import com.github.satoshun.android.mypractice.data.user.User;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

  private MainPresenter presenter;
  private TextView titleView;
  private Button reload;
  private ProgressBar progress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_act);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    titleView = (TextView) findViewById(R.id.title);
    reload = (Button) findViewById(R.id.reload);
    progress = (ProgressBar) findViewById(R.id.progress);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    reload.setOnClickListener(v -> presenter.bind());

    presenter = new MainPresenter(this, this);
    presenter.bind();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void showProgress() {
    reload.setVisibility(View.GONE);
    progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    reload.setVisibility(View.VISIBLE);
    progress.setVisibility(View.GONE);
  }

  @Override public void showUser(User user) {
    titleView.setText(user.getName());
  }

  @Override public void showError(String message) {
    titleView.setText(message);
  }
}
