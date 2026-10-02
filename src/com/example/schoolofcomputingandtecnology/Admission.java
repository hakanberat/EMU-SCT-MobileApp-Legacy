package com.example.schoolofcomputingandtecnology;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Admission extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admission); 
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		 
		 if (networkInfo != null && networkInfo.isConnected()) 
		 {
			 WebView myWebView = (WebView) findViewById(R.id.webview);
			 myWebView.loadUrl("http://ww1.emu.edu.tr/en/prospective-students/admission-requirements/c/1180");
		 
			 final ProgressDialog progress = ProgressDialog.show(this, "Loading...", "Please Wait....", true);
		      progress.show();
		      myWebView.setWebViewClient(new WebViewClient() {
		 
		         @Override
		         public void onPageFinished(WebView view, String url) {
		            super.onPageFinished(view, url);
		            Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
		            progress.dismiss();
		         }
		         public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		             Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
		             progress.dismiss();
		          }
		       });			 
		      }

		 else 
		 {
		    	Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_LONG).show();
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
		
	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.home:
	    	startActivity(new Intent(Admission.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }


}
