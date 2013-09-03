package com.barefoot.grid;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.*;

public class GridActivity extends FragmentActivity {

  private static final String TAG = GridActivity.class.getCanonicalName();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    getSupportFragmentManager().beginTransaction().add(new ComputeFragment(), "computeFragment").commit();
    createFilesInCache();
  }

  public void showSuccessToast(String data) {
    Toast.makeText(this, "Password is " + data, Toast.LENGTH_LONG).show();
  }

  public void createFilesInCache() {
    String[] files = new String[] {"base.html", "underscore.js", "input.js", "md5.js", "compute.js", "words.js"};
    FileOutputStream outputStream = null;
    InputStream inputStream = null;
    AssetManager assetManager = this.getAssets();
    File fileToWrite;
    String line;
    BufferedReader reader = null;
    for (String file : files ) {
      try {
        inputStream = assetManager.open("grid/"+file);
        fileToWrite = new File(this.getExternalFilesDir(null), file);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        outputStream = new FileOutputStream(fileToWrite);
        line = reader.readLine();
        while(line != null) {
          outputStream.write(line.getBytes());
          line = reader.readLine();
        }
      } catch (Exception e) {
        Log.e(TAG, e.getMessage());
      }
      finally {
        try {
          if(reader != null) reader.close();
        } catch (IOException e) {
        Log.e(TAG, e.getMessage());
        }

        try {
          if(outputStream != null) outputStream.close();
        } catch (IOException e) {
          Log.e(TAG, e.getMessage());
        }
      }
    }
  }
}
