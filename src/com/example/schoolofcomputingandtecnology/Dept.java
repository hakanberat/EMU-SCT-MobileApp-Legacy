package com.example.schoolofcomputingandtecnology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class Dept extends Activity{
	
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dept); 
	
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		expListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				//startActivity(new Intent(MainActivity.this, MIT.class));
				
				if(groupPosition == 0 & childPosition == 0) 
				{					
					startActivity(new Intent(Dept.this, MasterofIT.class));
		        } 
				if(groupPosition == 1 & childPosition == 0) 
				{
					startActivity(new Intent(Dept.this, BSIT.class));
		        }
				if(groupPosition == 2 & childPosition == 0) 
				{
					startActivity(new Intent(Dept.this, ConstTech.class));
		        }
				if(groupPosition == 2 & childPosition == 1) 
				{
					startActivity(new Intent(Dept.this, EETech.class));
		        }
				if(groupPosition == 2 & childPosition == 2) 
				{
					startActivity(new Intent(Dept.this, ComProg.class));
		        }
				if(groupPosition == 2 & childPosition == 3) 
				{
					startActivity(new Intent(Dept.this, BioTech.class));
		        }
				if(groupPosition == 2 & childPosition == 4) 
				{
					startActivity(new Intent(Dept.this, ATAPP.class));
		        }
				if(groupPosition == 3 & childPosition == 0) 
				{
					startActivity(new Intent(Dept.this, ATAPP2.class));
		        }
				if(groupPosition == 3 & childPosition == 1) 
				{
					startActivity(new Intent(Dept.this, BankInsr.class));
		        }
				if(groupPosition == 3 & childPosition == 2) 
				{
					startActivity(new Intent(Dept.this, BioTech2.class));
		        }
				if(groupPosition == 3 & childPosition == 3) 
				{
					startActivity(new Intent(Dept.this, CATD.class));
		        }
				if(groupPosition == 3 & childPosition == 4) 
				{
					startActivity(new Intent(Dept.this, ComProg2.class));
		        }
				if(groupPosition == 3 & childPosition == 5) 
				{
					startActivity(new Intent(Dept.this, ComProgIT.class));
		        }
				if(groupPosition == 3 & childPosition == 6) 
				{
					startActivity(new Intent(Dept.this, ConstTech2.class));
		        }
				if(groupPosition == 3 & childPosition == 7) 
				{
					startActivity(new Intent(Dept.this, EETech2.class));
		        }
				if(groupPosition == 3 & childPosition == 8) 
				{
					startActivity(new Intent(Dept.this, MAP.class));
		        }
				if(groupPosition == 3 & childPosition == 9) 
				{
					startActivity(new Intent(Dept.this, Medical.class));
		        }
				if(groupPosition == 3 & childPosition == 10) 
				{
					startActivity(new Intent(Dept.this, Office.class));
		        }
				
				/*else if(childPosition % 2 != 0) {
		            convertView = inflater.inflate(R.layout.listrow_details, null);
		        } else {
		            convertView = inflater.inflate(R.layout.listrow_details_color2, null);
		        }*/
				 
				
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Graduate Programs");
		listDataHeader.add("4 Year B.S. Programs");
		listDataHeader.add("3 Year Diploma Programs");
		listDataHeader.add("2 Year Diploma Programs");

		// Adding child data
		List<String> gp = new ArrayList<String>();
		gp.add("Master of IT");
		
		
		List<String> BS = new ArrayList<String>();
		BS.add("Information Technology");
		

		List<String> three = new ArrayList<String>();
		three.add("Construction and Technical Drawing Technologies ");
		three.add("Electrical and Electronics Technology");
		three.add("Computer Programming");
		three.add("Biomedical Equipment Technology");
		three.add("Accounting and Taxation Applications ");
		
		List<String> two = new ArrayList<String>();
		two.add("Accounting and Taxation Applications");
		two.add("Banking and Insurance");
		two.add("Biomedical Equipment Technology");
		two.add("Computer Aided Technical Drawing");
		two.add("Computer Programming");
		two.add("Computer Programming and Information Technology");
		two.add("Construction Technology");
		two.add("Electrical and Electronics Technology");
		two.add("Mapping and Cadastral Survey");
		two.add("Medical Documentation and Office Management ");
		two.add("Office Management");
		


		listDataChild.put(listDataHeader.get(0), gp); // Header, Child data
		listDataChild.put(listDataHeader.get(1), BS);
		listDataChild.put(listDataHeader.get(2), three);
		listDataChild.put(listDataHeader.get(3), two);
	
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
	    	startActivity(new Intent(Dept.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  } 

}
