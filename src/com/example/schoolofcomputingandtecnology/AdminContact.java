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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminContact extends Activity{
	
	
	ListView lst;
	ArrayList<String> lstrecords;
	ArrayAdapter<String> adapter;
	Button bdelete;
	TextView delete;
	String stringdelete;
	int code;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admincontact); 
		
		new task().execute();
		
		delete= (EditText) findViewById(R.id.editTextdelete);
		bdelete = (Button) findViewById(R.id.buttondelmsg);
		bdelete.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				stringdelete = delete.getText().toString().trim();
				
				if(stringdelete.length()>0 )
				{
						 ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
						 
						 if (networkInfo != null && networkInfo.isConnected()) 
						 {
							 new taskdelete().execute();
							 delete.setText("");
						 }
						 else 
						 {
						    	Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_LONG).show();
						 }
				}
				else
				{
					AlertDialog.Builder ab = new AlertDialog.Builder(AdminContact.this);
					ab.setMessage("Please Fill the Message Number Area!");
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
	    	startActivity(new Intent(AdminContact.this,Admin.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }
	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(AdminContact.this);
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
	      String url_select = "http://10.0.2.2/SCT/selectcontact.php";

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
	    	
	    String id ="", name = "", email="", msg="", lstvw="";
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
	   name = Jasonobject.getString("name");
	   email = Jasonobject.getString("email");
	   msg = Jasonobject.getString("msg");
	   lstvw =" Message Number :"+id+"\n Name :"+ name+"\n Email :"+email+"\n Message :"+msg;
	   
	   lstrecords.add(lstvw);	
	      //txt1.setText(id);
	      //txt2.setText(name);
	      //txt3.setText(surname);
	  
	   }
	   this.progressDialog.dismiss();
	   
	   adapter.notifyDataSetChanged();
	   
	   //Toast.makeText(getApplicationContext(), name+email+msg,Toast.LENGTH_LONG).show();
	   
	  } catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
	  }
	}
	}
	class taskdelete extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(AdminContact.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Deleting Message...");
	       progressDialog.show();
	       progressDialog.setOnCancelListener(new OnCancelListener() {
	 @Override
	  public void onCancel(DialogInterface arg0) {
	  taskdelete.this.cancel(true);
	    }
	 });
	     }
	    @Override
	    protected Void doInBackground(String... params) {
	    	Log.e("TAG0", "inbackground baslangici");
	    	
	    	  String de = delete.getText().toString();
	          
	          
	          ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		      param.add(new BasicNameValuePair("id", de));
	       
	          			
	      String url_insert = "http://10.0.2.2/SCT/deletecontact.php";

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
