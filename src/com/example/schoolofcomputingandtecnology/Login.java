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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	
	EditText username,password;
	Button login;
	int code;
	String stringusername, stringpassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); 
		
	
		
		username = (EditText) findViewById(R.id.editTextUsername);
		password = (EditText) findViewById(R.id.editTextPassword);
		login = (Button) findViewById(R.id.buttonSend);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stringusername = username.getText().toString().trim();
				stringpassword = password.getText().toString().trim();
				
				if(stringusername.length()>0 && stringpassword.length()>0 )
				{
						
						 ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
						 
						 if (networkInfo != null && networkInfo.isConnected()) 
						 {
							 new task().execute();
						 }
						 else 
						 {
						    	Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_LONG).show();
						 }
				}
				else
				{
					AlertDialog.Builder ab = new AlertDialog.Builder(Login.this);
					ab.setMessage("Please Fill All the Areas!");
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
	    	startActivity(new Intent(Login.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }
	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(Login.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Please wait...");
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
	    	String us = username.getText().toString();
	          String ps = password.getText().toString();
	          
	          ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		      param.add(new BasicNameValuePair("username", us));
	          param.add(new BasicNameValuePair("password", ps));
	    	
	      String url_select = "http://10.0.2.2/SCT/loginselect.php";

	      HttpClient httpClient = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost(url_select);
	      
	      

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

	    	String username1 ="", password1 = "";
	  // ambil data dari Json database
	  try {
		 
		  
		  JSONArray Jarray = new JSONArray(result);
		  for(int i=0;i<Jarray.length();i++)//
		   {
			  				JSONObject Jasonobject = null;
			  		
			  				Jasonobject = Jarray.getJSONObject(i);

		   
			  				username1 = Jasonobject.getString("username");
			  				password1 = Jasonobject.getString("password");
		   		  
			  				Log.e("for", "pw = "+username1+' '+password1);
		   
		  
          this.progressDialog.dismiss();
          
          if(stringusername.equals(username1.trim())  && stringpassword.equals(password1.trim())  )
        		  {        
        	  			Toast.makeText(getApplicationContext(),"WELCOME!" ,Toast.LENGTH_LONG).show();
        	  			startActivity(new Intent(Login.this,Admin.class));
        		  }
          else {          
        	  	//Toast.makeText(getApplicationContext(),"Invalid Username or Password!" ,Toast.LENGTH_LONG).show(); 
         		}
          
          }
	  		}
	  	 
          catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
	  }
	}
	}
}
