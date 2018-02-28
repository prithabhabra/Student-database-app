package com.example.prac7_1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends Activity {
	
	EditText enroll, subject;
	Button show, send;
	SQLiteDatabase mydb;
	String checkEnroll, checkSubject;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity3);
	        enroll=(EditText)findViewById(R.id.editText1);
	        subject=(EditText)findViewById(R.id.editText2);
	        show=(Button)findViewById(R.id.button1);
	        send=(Button)findViewById(R.id.button2);
	        
	        mydb=openOrCreateDatabase("Students", MODE_APPEND, null);
	        
	        show.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					checkEnroll=enroll.getText().toString();
					checkSubject=subject.getText().toString();
					Cursor c=mydb.rawQuery("SELECT * FROM StudentInfo WHERE enroll_no='"+checkEnroll+"' AND subject='"+checkSubject+"' ", null);
					c.moveToFirst();
					if(c.getCount()>0){
						int sum=Integer.parseInt(c.getString(4).toString())+Integer.parseInt(c.getString(5).toString());
						Toast.makeText(getApplicationContext(),"Total marks:"+sum, Toast.LENGTH_LONG).show();
					}
				}
			});
	        
	        send.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					checkEnroll=enroll.getText().toString();
					checkSubject=subject.getText().toString();
					Cursor c=mydb.rawQuery("SELECT * FROM StudentInfo WHERE enroll_no='"+checkEnroll+"' AND subject='"+checkSubject+"' ", null);
					Cursor c1=mydb.rawQuery("SELECT * FROM StudentBasicInfo WHERE enroll_no='"+checkEnroll+"'", null);
					c.moveToFirst();
					c1.moveToFirst();
					if(c.getCount()>0){
						String phoneNumber=c1.getString(2);
						int sum=Integer.parseInt(c.getString(4).toString())+Integer.parseInt(c.getString(5).toString());
						SmsManager sms = SmsManager.getDefault();
						sms.sendTextMessage(phoneNumber, null,"Marks: "+sum+" in "+checkSubject, null, null);
					}
				}
			});
	 }

}
