package com.example.schoolofcomputingandtecnology;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DelAnn extends Activity{
	
	ListView lst;
	ArrayList<String> lstrecords;
	ArrayAdapter<String> adapter;
	
	Button adelete;
	TextView atv;
	String stringandel;
	int code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delann); 
		
		new task().execute();
		
		atv = (TextView) findViewById(R.id.editTextdelann);
		adelete = (Button) findViewById(R.id.buttondelann);
		adelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stringandel = atv.getText().toString().trim();
				
				if(stringandel.length()>0 )
				{
						 ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
						 
						 if (networkInfo != null && networkInfo.isConnected()) 
						 {
							 new taskdeletean().execute();
							 atv.setText("");
						 }
						 else 
						 {
						    	Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_LONG).show();
						 }
				}
				else
				{
					AlertDialog.Builder ab = new AlertDialog.Builder(DelAnn.this);
					ab.setMessage("Please Fill the Ann Number Area!");
					ab.setCancelable(false);
					ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					
					ab.create().show();
				}
			}
		});
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
	    	startActivity(new Intent(DelAnn.this,Admin.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }

	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(DelAnn.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Fetching data...");
	       progressDialog.show();
	       progressDialog.setOnCancelListener(new OnCancelListener() {
	 @Override
	  public void onCancel(DialogInterface arg0) {
	  task.this.cancel(true);
	    }
	 });
	     }
	    @Override
	    protected Void doInBackground(String... params) {
	      String url_select = "http://10.0.2.2/sct/selectannoun.php";

	      HttpClient httpClient = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost(url_select);
	      
	      ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

	        try {
	     httpPost.setEntity(new UrlEncodedFormEntity(param));

	     HttpResponse httpResponse = httpClient.execute(httpPost);
	     HttpEntity httpEntity = httpResponse.getEntity();
	     //read content
	     is =  httpEntity.getContent();   

	     } catch (Exception e) {
	         Log.e("log_tag", "Error in http connection "+e.toString());
	     }
	    try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	     StringBuilder sb = new StringBuilder();
	     String line = "";     
	     while((line=br.readLine())!=null)
	     {
	        sb.append(line+"\n");
	     }
	      is.close();
	      result=sb.toString();    
	      
	     } catch (Exception e) {
	        // TODO: handle exception
	      	   Log.e("log_tag", "Error converting result "+e.toString());
	       }

	      return null;

	     }
	    protected void onPostExecute(Void v) {

	    	lst=(ListView) findViewById(R.id.listView1);
	    	lstrecords = new ArrayList<String>(); 
	    	adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,lstrecords);
	    	lst.setAdapter(adapter);
	    	lstrecords.clear();
	    	
	    String id= "", title = "", description="", date="", time="", place="", lstvw="";
	   //txt1 = (TextView)findViewById(R.id.txt1);
	   //txt2 = (TextView)findViewById(R.id.txt2);
	   //txt3 = (TextView)findViewById(R.id.txt3);
	   
	  // ambil data dari Json database
	  try {
		 
	   JSONArray Jarray = new JSONArray(result);
	      
	   for(int i=0;i<Jarray.length();i++)//
	   {
	   JSONObject Jasonobject = null;
	   
	   Jasonobject = Jarray.getJSONObject(i);

	   //get an output on the screen
	   id = Jasonobject.getString("id");
	   title = Jasonobject.getString("title");
	   description = Jasonobject.getString("description");
	   date = Jasonobject.getString("date");
	   time = Jasonobject.getString("time");
	   place = Jasonobject.getString("place");
	   lstvw = " Number :"+id+"\n Title :"+title+"\n Description :"+description+"\n Date :"+date+"\n Time :"+time+"\n Place :"+place;
	   
	   lstrecords.add(lstvw);	
	      //txt1.setText(id);
	      //txt2.setText(name);
	      //txt3.setText(surname);
	  
	   }
	   this.progressDialog.dismiss();
	   
	   adapter.notifyDataSetChanged();
	   
	   
	   
	  } catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
	  }
	}
	}
	class taskdeletean extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(DelAnn.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Deleting Message...");
	       progressDialog.show();
	       progressDialog.setOnCancelListener(new OnCancelListener() {
	 @Override
	  public void onCancel(DialogInterface arg0) {
		 taskdeletean.this.cancel(true);
	    }
	 });
	     }
	    @Override
	    protected Void doInBackground(String... params) {
	    	Log.e("TAG0", "inbackground baslangici");
	    	
	    	  String de = atv.getText().toString();
	          
	          
	          ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		      param.add(new BasicNameValuePair("id", de));
	       
	          			
	      String url_insert = "http://10.0.2.2/SCT/deleteann.php";

	      HttpClient httpClient = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost(url_insert);
	      
	      Log.e("TAG1", "params: "+param.toString());
	      Log.e("TAG2", "set entity oncesi");
	        try {
	     httpPost.setEntity(new UrlEncodedFormEntity(param));

	     HttpResponse httpResponse = httpClient.execute(httpPost);
	     HttpEntity httpEntity = httpResponse.getEntity();
	     //read content
	     is =  httpEntity.getContent();   

	     } catch (Exception e) {
	         Log.e("log_tag", "Error in http connection "+e.toString());
	     }
	        Log.e("TAG3", "loop oncesi");  
	    try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	     StringBuilder sb = new StringBuilder();
	     String line = "";     
	     while((line=br.readLine())!=null)
	     {
	        sb.append(line+"\n");
	     }
	      is.close();
	      result=sb.toString();    
	      
	      Log.e("TAG4", sb.toString());
	     } catch (Exception e) {
	        // TODO: handle exception
	      	   Log.e("log_tag", "Error converting result "+e.toString());
	       }

	      return null;

	     }
	    
	    
	    protected void onPostExecute(Void v) {
	
	    	 
	  // ambil data dari Json database
	  try {
		 
		  JSONObject json_data = new JSONObject(result);
          code=(json_data.getInt("code"));
          Log.e("TAG5", String.valueOf(code));
          
          this.progressDialog.dismiss();
          
          if(code==1) {         
        	  Toast.makeText(getApplicationContext(),"Deleted!" ,Toast.LENGTH_LONG).show();
              new task().execute();
          }
          else           
        	  Toast.makeText(getApplicationContext(),"Not Deleted!" ,Toast.LENGTH_LONG).show();
          
	  
	  } catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
	  }
	}
}}
