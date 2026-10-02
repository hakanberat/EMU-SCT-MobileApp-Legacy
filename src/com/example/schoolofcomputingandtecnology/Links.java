package com.example.schoolofcomputingandtecnology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Links extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.links); 
		
		TextView tvsct = (TextView) findViewById(R.id.textView1);
		tvsct.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvpdram = (TextView) findViewById(R.id.textView2);
		tvpdram.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvlibrary = (TextView) findViewById(R.id.textView3);
		tvlibrary.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvcs = (TextView) findViewById(R.id.textView4);
		tvcs.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvhealth = (TextView) findViewById(R.id.textView5);
		tvhealth.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvfamily = (TextView) findViewById(R.id.textView6);
		tvfamily.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvacademic = (TextView) findViewById(R.id.textViewActivities);
		tvacademic.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvstdportal = (TextView) findViewById(R.id.textView8);
		tvstdportal.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView tvregister = (TextView) findViewById(R.id.textView9);
		tvregister.setMovementMethod(LinkMovementMethod.getInstance());
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
	    	startActivity(new Intent(Links.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }


}
