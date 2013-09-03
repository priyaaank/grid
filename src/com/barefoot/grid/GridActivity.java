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

  public void showSuccessToast() {
    Toast.makeText(this, "Function was executed as you'd want", Toast.LENGTH_LONG).show();
  }
}
