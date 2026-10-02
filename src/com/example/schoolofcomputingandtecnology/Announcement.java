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
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Announcement extends Activities{
	
	ListView lst;
	ArrayList<String> lstrecords;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.announcement); 
		
		new task().execute();
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
	    	startActivity(new Intent(Announcement.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }

	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(Announcement.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Loading Announcements...");
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
	    	
	    String title = " ", description=" ", date=" ", time=" ", place=" ", lstvw=" ";
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
	   title = Jasonobject.getString("title");
	   description = Jasonobject.getString("description");
	   date = Jasonobject.getString("date");
	   time = Jasonobject.getString("time");
	   place = Jasonobject.getString("place");
	   lstvw = " Title :"+title+"\n Description :"+description+"\n Date :"+date+"\n Time :"+time+"\n Place :"+place;
	   
	   lstrecords.add(lstvw);	
	      //txt1.setText(id);
	      //txt2.setText(name);
	      //txt3.setText(surname);
	  
	   }
	   this.progressDialog.dismiss();
	   
	   adapter.notifyDataSetChanged();
	   
	   Toast.makeText(getApplicationContext(), title+" "+description+" "+date+" "+time+" "+place,Toast.LENGTH_LONG).show();
	   
	  } catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
   	    

	  }
	}
	}

}

