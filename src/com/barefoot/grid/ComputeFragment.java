package com.barefoot.grid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class ComputeFragment extends Fragment {

  public static final int WEB_VIEW_ID = 625;
  public static final String COMPUTE_INTERFACE = "gridInterface";
  private static final String TAG = ComputeFragment.class.getCanonicalName();
  private WebView hiddenWebView;
  private ComputeFragment.JavascriptInterface javascriptInterface;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return null;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    hiddenWebView = new WebView(this.getActivity().getApplicationContext());
    hiddenWebView.setId(WEB_VIEW_ID);
    javascriptInterface = new JavascriptInterface(this.getActivity().getApplicationContext());
    hiddenWebView.addJavascriptInterface(javascriptInterface, COMPUTE_INTERFACE);
    hiddenWebView.getSettings().setJavaScriptEnabled(true);
    hiddenWebView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        initiateJsCallback();
      }
    });
    hiddenWebView.loadUrl("file:///android_asset/grid/base.html");
  }

  private void initiateJsCallback() {
    hiddenWebView.loadUrl("javascript:initiate();");
  }

  class JavascriptInterface {

    Context applicationContext;

    public JavascriptInterface(Context applicationContext) {
      this.applicationContext = applicationContext;
    }

    public void log(String msg) {
      Log.d(TAG, msg);
    }

    public void computedData(String jsonData) {
      String output = null;
      try {
        output = new JSONObject(jsonData).get("output").toString();
      } catch (JSONException e) {
        Log.e(TAG, e.getMessage());
      }
      ((GridActivity) getActivity()).showSuccessToast(output);
    }

  }
}
