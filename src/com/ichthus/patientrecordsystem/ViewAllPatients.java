package com.ichthus.patientrecordsystem;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAllPatients extends Activity implements OnItemClickListener, OnItemSelectedListener, OnClickListener {
	
	AutoCompleteTextView tv;
	ListView lv;
	private DBHelper dbHelper;
	Cursor cursor;
	String[] search;
	CustomAdapter ca;
	ArrayAdapter<String> ad;
	Typeface tf;
	Spinner spinner;
	Button home;
	
	final String[] categories = {
			"Firstname", "Lastname", "Last check up"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewallpatients);
		
		initControls();
		
	}
	
	private void initControls(){
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String> 
			(this,android.R.layout.simple_spinner_item, categories);
		spinner = (Spinner) findViewById (R.id.spCategory);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(-1);
		spinner.setOnItemSelectedListener(this);
		lv = (ListView) findViewById (R.id.lvListOfPatients);
		lv.setOnItemClickListener(this);
		lv.setTextFilterEnabled(true);
        lv.setFastScrollEnabled(true);
		home = (Button) findViewById (R.id.btHome);
		home.setOnClickListener(this);
		
		tv = (AutoCompleteTextView) findViewById (R.id.etSearch);

		displayResults();
	}

	@Override
	public void onItemClick(AdapterView<?> lv, View v, int position, long id) {
		// TODO Auto-generated method stub
		   // Get the cursor, positioned to the corresponding row in the result set
		   cursor = (Cursor) lv.getItemAtPosition(position);
		
		   String strFname = cursor.getString(cursor.getColumnIndexOrThrow(
				   DBHelper.KEY_FNAME));
			//	   DBHelper.KEY_FNAME + "|| ' ' ||" + DBHelper.KEY_LNAME));
		   String strLname = cursor.getString(cursor.getColumnIndexOrThrow(
				   DBHelper.KEY_LNAME));
		   String strLastffup = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_LASTFFUP));
		   String strDiagnosis = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_DIAGNOSIS));
		   Toast.makeText(getApplicationContext(),
		     strFname + " " + strLname, Toast.LENGTH_SHORT).show();
		  
		   Intent i = new Intent(this, ViewPatient.class);
		   Bundle extras = new Bundle();
		   extras.putString("patientname", strFname);
		   extras.putString("patientLname", strLname);
		   extras.putString("lastffup", strLastffup);
		   extras.putString("diagnosis", strDiagnosis);
		   i.putExtras(extras);
		   startActivityForResult(i, 1);
	}
	
	private void displayResults(){
			
			// TODO CheckDBConnection
			checkDatabaseConnection();

			// TODO 
	        cursor = dbHelper.getAllPatients();
	        ca = new CustomAdapter(this, cursor);
	        lv.setAdapter(ca); 

	        tv.setThreshold( 2 );
	        tv.setTextColor( Color.BLACK );

	        tv.addTextChangedListener(new TextWatcher() {
			   public void afterTextChanged(Editable s) {
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start,
			     int count, int after) {
			   }
			 
			   public void onTextChanged(CharSequence s, int start,
			     int before, int count) {
	                ca.getFilter().filter(s.toString());
			   		}
			  });
			   
			  	ca.setFilterQueryProvider(new FilterQueryProvider() {
			         public Cursor runQuery(CharSequence constraint) {
			             return dbHelper.fetchPatientsByName(constraint.toString());
			         }
			  }); 
		   
  
	}

	private void checkDatabaseConnection() {
		// TODO Auto-generated method stub
		dbHelper = new DBHelper(this);

        try {

                dbHelper.createDataBase();

        } catch (IOException ioe) {

                throw new Error("Unable to create database");

        }

        try {

                dbHelper.openDataBase();

        } catch (SQLException sqle) {

                throw sqle;

        }
	}

	public class CustomAdapter extends CursorAdapter implements Filterable
	{
		LayoutInflater inflater;
		@SuppressWarnings("deprecation")
		public CustomAdapter(Context context, Cursor c) {
			super(context, c);
			inflater = LayoutInflater.from(context);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView tv1 = (TextView)view.findViewById(R.id.fullname);
		TextView tv2 = (TextView)view.findViewById(R.id.lastffup);
		TextView tv3 = (TextView)view.findViewById(R.id.diagnosis);
		TextView tv4 = (TextView)view.findViewById(R.id.lname);
		
		tf = Typeface.createFromAsset(getAssets(), "fonts/roboto-regular.ttf");
		
		tv1.setTypeface(tf);
		tv2.setTypeface(tf);
		tv3.setTypeface(tf);
		tv4.setTypeface(tf);
		
		int position = cursor.getPosition();
		int mCount = cursor.getCount();
		
		if (position == 0 && mCount == 1) {
            view.setBackgroundResource(R.drawable.selector_rounded_corner_top_bottom);
        } else if (position == 0) {
            view.setBackgroundResource(R.drawable.selector_rounded_corner_top);
        } else if (position == mCount - 1) {
            view.setBackgroundResource(R.drawable.selector_rounded_corner_bottom);
        } else {
            view.setBackgroundResource(R.drawable.selector_list_entry_middle);
        }
	 
		String index = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ID));
		Log.d(index, index);
		//tv1.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_FNAME + "|| ' ' ||" + DBHelper.KEY_LNAME)));
		tv1.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_FNAME)));
		tv2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LASTFFUP)));
		tv3.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DIAGNOSIS)));
		tv4.setText(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LNAME)));
	}
	 
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return inflater.inflate(R.layout.custom_row, parent, false);
	 }
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			cursor = dbHelper.sortByFname();
			updateList();
			lv.setAdapter(ca); 
			break;
		case 1:
			cursor = dbHelper.sortByLname();
			updateList();
			lv.setAdapter(ca); 
			break;
		case 2:
			cursor = dbHelper.sortByLastFFUp();
			updateList();
			lv.setAdapter(ca); 
			break;
		default:
			cursor = dbHelper.getAllPatients();
	        ca = new CustomAdapter(this, cursor);
	        lv.setAdapter(ca);
			break;
		}
	}
	
	private void updateList() {
		// TODO Auto-generated method stub
		ca.changeCursor(cursor);
	}

	@Override
	public void onNothingSelected(AdapterView<?> v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		dbHelper.close(); // close DB
		cursor.close(); // close cursor
		tv.getText().clear(); // clear user input
		super.onDestroy();
	}

}
