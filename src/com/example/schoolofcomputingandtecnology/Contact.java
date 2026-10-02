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

public class Contact extends Activity {
	int code;
	EditText name, email, msg;
	String stringname, stringemail, stringmsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact); 
		

		  name = (EditText) findViewById(R.id.editTextName);
	      email = (EditText) findViewById(R.id.editTextEmail);
	      msg = (EditText) findViewById(R.id.editTextMsg);
		  
		  Button b= (Button) findViewById(R.id.buttonSend);
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					stringname = name.getText().toString().trim();
					stringemail = email.getText().toString().trim();
					stringmsg = msg.getText().toString().trim();
					
			if(stringname.length()>0 && stringemail.length()>0 && stringmsg.length()>0)
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
				AlertDialog.Builder ab = new AlertDialog.Builder(Contact.this);
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

 class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(Contact.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Sending Message...");
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
	    	Log.e("TAG0", "inbackground baslangici");
	    	  String na = name.getText().toString();
	          String em = email.getText().toString();
	          String ms = msg.getText().toString();
	          
	          ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		      param.add(new BasicNameValuePair("name", na));
	          param.add(new BasicNameValuePair("email", em));
	          param.add(new BasicNameValuePair("msg", ms));
		    
	          			
	      String url_insert = "http://10.0.2.2/SCT/insertcontact.php";

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
          
          if(code==1)  {        
        	  Toast.makeText(getApplicationContext(),"Sent!" ,Toast.LENGTH_LONG).show();
	    	startActivity(new Intent(Contact.this,MainActivity.class));
          }
          else           
        	  Toast.makeText(getApplicationContext(),"Not Sent!" ,Toast.LENGTH_LONG).show();
          
	  
	  } catch (Exception e) {
	   // TODO: handle exception
	   Log.e("log_tag", "Error parsing data "+e.toString());
	  }
	}
	
public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		
	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.home:
	    	startActivity(new Intent(Contact.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
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
	    	startActivity(new Intent(Contact.this,MainActivity.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  } 

}
