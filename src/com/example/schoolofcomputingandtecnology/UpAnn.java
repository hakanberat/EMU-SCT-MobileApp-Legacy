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
import android.widget.Toast;

public class UpAnn extends Activity{
	
	ListView lst;
	ArrayList<String> lstrecords;
	ArrayAdapter<String> adapter;
	int code;
	EditText aid, atitle, adescription, adate, atime, aplace;
	String stringaid, stringatitle, stringadescription, stringadate, stringatime,stringaplace;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upann); 
		
		new task().execute();
		aid = (EditText) findViewById(R.id.editText1id);
		atitle = (EditText) findViewById(R.id.editText2title);
		adescription = (EditText) findViewById(R.id.editText3decr);
		adate = (EditText) findViewById(R.id.editText4date);
		atime = (EditText) findViewById(R.id.editText5time);
		aplace = (EditText) findViewById(R.id.editText6Place);
		
		Button b= (Button) findViewById(R.id.button1upan);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stringaid = aid.getText().toString().trim();
				stringatitle = atitle.getText().toString().trim();
				stringadescription = adescription.getText().toString().trim();
				stringadate = adate.getText().toString().trim();
				stringatime = atime.getText().toString().trim();
				stringaplace = aplace.getText().toString().trim();
				
				if(stringaid.length()>0 && stringatitle.length()>0 && stringadescription.length()>0 && stringadate.length()>0 && stringatime.length()>0 && stringaplace.length()>0)
				{
						
						 ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
						 
						 if (networkInfo != null && networkInfo.isConnected()) 
						 {
							 new taskup().execute();
							 aid.setText("");
							 atitle.setText("");
							 adescription.setText("");
							 adate.setText("");
							 atime.setText("");
							 aplace.setText("");
							 
						 }
						 else 
						 {
						    	Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_LONG).show();
						 }
				}
				else
				{
					AlertDialog.Builder ab = new AlertDialog.Builder(UpAnn.this);
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
	    	startActivity(new Intent(UpAnn.this,Admin.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }

	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(UpAnn.this);
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
	    	
	    String id="", title = "", description="", date="", time="", place="", lstvw="";
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
	   lstvw = " Number : "+id+"\n Title "+title+"\n Description :"+description+"\n Date :"+date+"\n Time :"+time+"\n Place :"+place;
	   
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
	class taskup extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(UpAnn.this);
	    InputStream is = null ;
	    String result = "";
	    protected void onPreExecute() {
	       progressDialog.setMessage("Saving Announcement...");
	       progressDialog.show();
	       progressDialog.setOnCancelListener(new OnCancelListener() {
	 @Override
	  public void onCancel(DialogInterface arg0) {
	  taskup.this.cancel(true);
	    }
	 });
	     }
	    @Override
	    protected Void doInBackground(String... params) {
	    	Log.e("TAG0", "inbackground baslangici");
	    	
	    	  String ad = aid.getText().toString();
	    	  String ti = atitle.getText().toString();
	          String de = adescription.getText().toString();
	          String dt = adate.getText().toString();
	          String tm = atime.getText().toString();
	          String pl = aplace.getText().toString();
	          
	          ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		      param.add(new BasicNameValuePair("id", ad));
		      param.add(new BasicNameValuePair("title", ti));
	          param.add(new BasicNameValuePair("description", de));
	          param.add(new BasicNameValuePair("date", dt));
	          param.add(new BasicNameValuePair("time", tm));
	          param.add(new BasicNameValuePair("place", pl));
		    
	          			
	      String url_insert = "http://10.0.2.2/SCT/updateann.php";

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
          
          if(code==1)   {       
        	  Toast.makeText(getApplicationContext(),"Updated!" ,Toast.LENGTH_LONG).show();
          new task().execute();}
          else           
        	  Toast.makeText(getApplicationContext(),"Not Updated!" ,Toast.LENGTH_LONG).show();
          
	  
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
	    	startActivity(new Intent(UpAnn.this,Admin.class));
	      break;
	    // action with ID action_settings was selected
	    
	    default:
	      break;
	    }
	    return true;
	  }



}}




