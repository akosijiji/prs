package com.ichthus.patientrecordsystem;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class ViewAllPatients extends Activity implements OnItemClickListener {
	
	EditText search;
	ListView lv;
	private SimpleCursorAdapter dataAdapter;
	private DBHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewallpatients);
		
		initControls();
		
	}
	
	private void initControls(){
		
		lv = (ListView) findViewById (R.id.lvListOfPatients);
		lv.setOnItemClickListener(this);
		
		search = (EditText) findViewById (R.id.etSearch);
		
		displayResults();
	}

	private void displayResults(){
		
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
        Cursor cursor = dbHelper.getAllPatients();
        String[] data = new String[]{
        		//DBHelper.KEY_LNAME,
        		DBHelper.KEY_FNAME, DBHelper.KEY_DIAGNOSIS, DBHelper.KEY_LASTFFUP};
        
        int[] to = new int[] {R.id.fullname, R.id.diagnosis, R.id.lastffup};
        
        dataAdapter = new SimpleCursorAdapter(this, R.layout.custom_row, cursor, data, to, 0);
        
        
        lv.setAdapter(dataAdapter);
        lv.setTextFilterEnabled(true);
        
        dbHelper.close();
        
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		
	}

}
