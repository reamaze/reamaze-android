package com.reamaze.sdk;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;

public class ReamazeActivity extends ActionBarActivity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reamaze);
		
		setTitle("Help Center");
		
		Intent intent = getIntent();
		String brand = intent.getStringExtra("brand");
		
		if (brand == null || brand.isEmpty());
			brand = "reamaze";

		final WebView webView = (WebView)findViewById(R.id.reamaze_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient() {
			private ProgressDialog dialog;
	        private int       webViewPreviousState;
	        private final int PAGE_STARTED    = 0x1;
	        private final int PAGE_REDIRECTED = 0x2;

	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
	            webViewPreviousState = PAGE_REDIRECTED;
	            webView.loadUrl(urlNewString);
	            return true;
	        }

	        @Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) {
	            super.onPageStarted(view, url, favicon);
	            webViewPreviousState = PAGE_STARTED;
	            if (dialog == null || !dialog.isShowing())
	                dialog = ProgressDialog.show(ReamazeActivity.this, "", "Loading", true, true);
	        }

	        @Override
	        public void onPageFinished(WebView view, String url) {
	            if (webViewPreviousState == PAGE_STARTED && dialog != null) {
	                dialog.dismiss();
	                dialog = null;
	            }
	        }
		});
		
		Map<String,String> headers = new HashMap<String, String>(){{
			put("X-Reamaze-Embed", "Android"); 
		}};
		
		webView.loadUrl("http://" + brand + ".reamaze.com", headers);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reamaze, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.reamaze_exit) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed()
	{
		final WebView webView = (WebView)findViewById(R.id.reamaze_webview);

	    if(webView.canGoBack())
	        webView.goBack();
	    else
	        super.onBackPressed();
	}

}
