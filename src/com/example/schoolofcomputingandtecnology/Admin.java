package com.example.schoolofcomputingandtecnology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Admin extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin); 
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
	    	startActivity(new Intent(Admin.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }
	public void clickMsg (View oViev){
		startActivity(new Intent(Admin.this,AdminContact.class));
	}
	
	public void clickAnnouncement (View oViev){
		startActivity(new Intent(Admin.this,AdminAnnouncement.class));
	}
	
	public void deleann (View oViev){
		startActivity(new Intent(Admin.this,DelAnn.class));
	}
	public void updateannoun (View oViev){
		startActivity(new Intent(Admin.this,UpAnn.class));
	}
	
	
	
	
}
