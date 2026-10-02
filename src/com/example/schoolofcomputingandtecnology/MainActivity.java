package com.example.schoolofcomputingandtecnology;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton dept = (ImageButton) findViewById(R.id.imageButtonDept);
		dept.setOnClickListener(this);
		
		ImageButton info = (ImageButton) findViewById(R.id.imageButtonInfo);
		info.setOnClickListener(this);
		
		ImageButton contact = (ImageButton) findViewById(R.id.imageButtonContact);
		contact.setOnClickListener(this);
		
		ImageButton login = (ImageButton) findViewById(R.id.imageButtonLogin);
		login.setOnClickListener(this);
		
		ImageButton links = (ImageButton) findViewById(R.id.imageButtonLinks);
		links.setOnClickListener(this);
		
		ImageButton about = (ImageButton) findViewById(R.id.imageButtonAbout);
		about.setOnClickListener(this);
		
		ImageButton staff = (ImageButton) findViewById(R.id.imageButtonStaff);
		staff.setOnClickListener(this);
		
		ImageButton admission = (ImageButton) findViewById(R.id.imageButtonAdmission);
		admission.setOnClickListener(this);
		
			
		ImageButton announcement = (ImageButton) findViewById(R.id.imageButtonAnnouncements);
		announcement.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.imageButtonDept)
		{
			startActivity(new Intent(MainActivity.this,Dept.class));
		}
		if(v.getId()==R.id.imageButtonInfo)
		{
			Dialog d = new Dialog(MainActivity.this);
			d.setContentView(R.layout.info);
			d.setTitle("Version 1.0.0");
			d.show();
		}
		if(v.getId()==R.id.imageButtonContact)
		{
			startActivity(new Intent(MainActivity.this,Contact.class));
			
		}
		if(v.getId()==R.id.imageButtonLogin)
		{
			startActivity(new Intent(MainActivity.this,Login.class));
			
		}
		if(v.getId()==R.id.imageButtonLinks)
		{
			startActivity(new Intent(MainActivity.this,Links.class));
			
		}
		if(v.getId()==R.id.imageButtonAbout)
		{
			startActivity(new Intent(MainActivity.this,About.class));
			
		}
		if(v.getId()==R.id.imageButtonStaff)
		{
			startActivity(new Intent(MainActivity.this,Staff.class));
			
		}
		if(v.getId()==R.id.imageButtonAdmission)
		{
			startActivity(new Intent(MainActivity.this,Admission.class));
			
		}
		
		if(v.getId()==R.id.imageButtonAnnouncements)
		{
			startActivity(new Intent(MainActivity.this,Announcement.class));
			
		}
	}

}
