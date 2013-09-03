package com.barefoot.grid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class GridActivity extends FragmentActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    getSupportFragmentManager().beginTransaction().add(new ComputeFragment(), "computeFragment").commit();
  }

  public void showSuccessToast(String data) {
    Toast.makeText(this, "Password is " + data, Toast.LENGTH_LONG).show();
  }
}
