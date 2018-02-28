package com.example.prac7_1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends Activity {
	
	EditText enroll, sem, subject, test1, test2;
	Button save, show, next;
	SQLiteDatabase mydb;
	String checkEnroll;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity2);
	        enroll=(EditText)findViewById(R.id.editText1);
	        subject=(EditText)findViewById(R.id.editText2);
	        sem=(EditText)findViewById(R.id.editText3);
	        test1=(EditText)findViewById(R.id.editText4);
	        test2=(EditText)findViewById(R.id.editText5);
	        save=(Button)findViewById(R.id.button1);
	        show=(Button)findViewById(R.id.button2);
	        next=(Button)findViewById(R.id.button3);
	        
	        mydb=openOrCreateDatabase("Students", MODE_APPEND, null);
	        
	        save.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					checkEnroll=enroll.getText().toString();
					Cursor c=mydb.rawQuery("SELECT * FROM StudentBasicInfo WHERE enroll_no='"+checkEnroll+"'", null);
					c.moveToFirst();
					if(c.getCount()>0){
						Toast.makeText(getApplicationContext(), "name:"+c.getString(0)+"  enroll:"+c.getString(1)+"  phone:"+c.getString(2), Toast.LENGTH_LONG).show();
						mydb.execSQL("CREATE TABLE IF NOT EXISTS StudentInfo(id INTEGER PRIMARY KEY AUTOINCREMENT, enroll_no TEXT, subject TEXT , sem TEXT, test1 TEXT, test2 TEXT);");
						mydb.execSQL("INSERT INTO StudentInfo VALUES(NULL,'"+enroll.getText()+"','"+subject.getText()+"','"+sem.getText()+"','"+test1.getText()+"','"+test2.getText()+"');");
					}
					else
						Toast.makeText(getApplicationContext(),"Not found", Toast.LENGTH_LONG).show();
				}
	        });
	        show.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//displaying
					Cursor c=mydb.rawQuery("SELECT * FROM StudentInfo", null);
					Toast.makeText(getApplicationContext(),"rows:"+c.getCount(), Toast.LENGTH_LONG).show();
					while(c.moveToNext()){
					
							Toast.makeText(getApplicationContext(), "enroll:"+c.getString(1)+"  subject:"+c.getString(2)+"  sem:"+c.getString(3)+"  test1:"+c.getString(4)+"  test2:"+c.getString(5), Toast.LENGTH_LONG).show();
						
					}
					
				}
			});
	        next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getApplicationContext(),Activity3.class);
					startActivity(i);
				}
			});
	        
	 }
}
