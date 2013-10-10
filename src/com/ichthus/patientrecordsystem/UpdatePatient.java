package com.ichthus.patientrecordsystem;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdatePatient extends Activity implements OnClickListener {

	Button home, save, cancel;
	ImageButton search;
	EditText fname, lname, diagnosis;
	String etFname, etLname, etDiagnosis, patient, strPatient_id;
	long patient_id;
	final String[] categories = {
			"Firstname",
			"Lastname",
			"Patient ID"
	};
	ArrayAdapter<String> adapter;
	
	DBHelper dbHelper = new DBHelper(this);
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.updatepatient);
		
		initControls();
		checkDB();
		
	}

	private void initControls() {
		// TODO Auto-generated method stub
		home = (Button) findViewById (R.id.btHome);
		save = (Button) findViewById (R.id.btSavePatient);
		cancel = (Button) findViewById (R.id.btCancelPatient);
		search = (ImageButton) findViewById (R.id.btSearch);
		
		fname = (EditText) findViewById (R.id.etFirstname);
		lname = (EditText) findViewById (R.id.etLastname);
		diagnosis = (EditText) findViewById (R.id.etDiagnosis);
		
		home.setOnClickListener(this);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
		search.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.btHome:
				finish();
				break;
			case R.id.btSavePatient:
				
				etFname = fname.getText().toString();
				etLname = lname.getText().toString();
				etDiagnosis = diagnosis.getText().toString();
				
				if(etFname.equals("") || etLname.equals("") || etDiagnosis.equals("")){
					emptyFields();
				} else {
					patient_id = Long.valueOf(strPatient_id);
					dbHelper.updateEntry( patient_id, fname.getText().toString() );
					Toast.makeText(this, "Record has successfully updated.", 
							Toast.LENGTH_SHORT).show();
				}
				
				
				break;
			case R.id.btCancelPatient:
				break;
			case R.id.btSearch:
				
				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle("Patient Record System");
				alert.setMessage("Search a patient by: ");
				
				// Set the LinearLayout
				final LinearLayout layout = new LinearLayout(this);
			    layout.setOrientation(LinearLayout.VERTICAL);
				
				final EditText input = new EditText(this);
				
				adapter = new ArrayAdapter<String> (this, 
						android.R.layout.simple_spinner_item, categories);
				final Spinner spinner = new Spinner(this);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(adapter);
				
				// Add the views to the LinearLayout
			    layout.addView(spinner);
			    layout.addView(input);
				
			    // Then add the LinearLayout to AlertDialog
				alert.setView(layout);

				alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					 	patient = input.getText().toString();

					  	displayResult();
					  
				        dialog.dismiss();
				  }
				});
				

				alert.show();
				
				break;
		}
	}
	
	private void checkDB(){
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
	
	private void displayResult(){
		
        cursor = dbHelper.fetchPatientsByName(patient);
        
        strPatient_id = cursor.getString(0);
        String strFname = cursor.getString(1);
        String strLname = cursor.getString(2);
        String strDiagnosis = cursor.getString(3);

        Log.d("Patient ID", "Patient ID is " + strPatient_id);
        fname.setText(strFname);
        lname.setText(strLname);
        diagnosis.setText(strDiagnosis);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cursor.close();
		dbHelper.close();
	}
	
	private void emptyFields(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Patient Record System");
		alert.setMessage("There is/are empty field/s! Please input to proceed.");
		
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		        dialog.dismiss();
		  }
		});
		

		alert.show();
	}

}
