package com.example.prac7_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText editText1, editText2, editText3;
	Button save, show, next;
	SQLiteDatabase mydb;
	String name=null, enroll=null, phone=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        save=(Button)findViewById(R.id.button1);
        show=(Button)findViewById(R.id.button2);
        next=(Button)findViewById(R.id.button3);
        
        mydb=openOrCreateDatabase("Students", MODE_APPEND, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS StudentBasicInfo(name TEXT , enroll_no TEXT PRIMARY KEY, phone_no TEXT );");
        
        save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count=0;
				name = editText1.getText().toString();
				enroll = editText2.getText().toString();
				phone = editText3.getText().toString(); 
				try{
				mydb.execSQL("INSERT INTO StudentBasicInfo VALUES('"+name+"','"+
	            		enroll+"','"+phone+"');");
				}catch(SQLiteConstraintException e){
					Toast.makeText(getApplicationContext(),"Enrollment number exists!", Toast.LENGTH_LONG).show();
					count++;
				}
				if(count==0)
					Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_LONG).show();
			}
        });
        show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//displaying
				Cursor c=mydb.rawQuery("SELECT * FROM StudentBasicInfo", null);
				Toast.makeText(getApplicationContext(),"rows:"+c.getCount(), Toast.LENGTH_LONG).show();
				while(c.moveToNext()){
				
						Toast.makeText(getApplicationContext(), "name:"+c.getString(0)+" enroll:"+c.getString(1)+" phone:"+c.getString(2), Toast.LENGTH_LONG).show();
					
				}
				
			}
		});
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Activity2.class);
				startActivity(i);
			}
		});
        
    }
}
