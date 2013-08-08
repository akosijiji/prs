package com.ichthus.patientrecordsystem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends Activity implements OnClickListener {
	
	Button save, cancel, home;
	EditText firstname, lastname, bday, telphone, lastffup,
				diagnosis, bp, heart, diabetes, lung, brain, 
				muscle, abdomen, urine, gout, prescription;
	
	String strFname, strLname, strBday, strTelphone, strLastffup, strDiagnosis, strBP, 
				strHeart, strDiabetes, strLung, strBrain, strMuscle, strAbdomen, 
				strUrine, strGout, strPrescription;
	
	private DBHelper saveRecord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addpatient);
		
		// Call Controls
		initControls();
		
	}
	

	private void initControls() {
		// TODO Auto-generated method stub
		save = (Button) findViewById (R.id.btSavePatient);
		cancel = (Button) findViewById (R.id.btCancelPatient);
		home = (Button) findViewById (R.id.btHome);
		
		firstname = (EditText) findViewById (R.id.etFirstname);
		lastname = (EditText) findViewById (R.id.etLastname);
		bday = (EditText) findViewById (R.id.etBday);
		telphone = (EditText) findViewById (R.id.etTelPhone);
		lastffup = (EditText) findViewById (R.id.etLastFFUp);
		diagnosis = (EditText) findViewById (R.id.etDiagnosis);
		bp = (EditText) findViewById (R.id.etBP);
		heart = (EditText) findViewById (R.id.etHeart);
		diabetes = (EditText) findViewById (R.id.etDiabetes);
		lung = (EditText) findViewById (R.id.etLung);
		brain = (EditText) findViewById (R.id.etBrain);
		muscle = (EditText) findViewById (R.id.etMuscle);
		abdomen = (EditText) findViewById (R.id.etAbdomen);
		urine = (EditText) findViewById (R.id.etUrine);
		gout = (EditText) findViewById (R.id.etGout);
		prescription = (EditText) findViewById (R.id.etPrescription);

		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());

		SimpleDateFormat df = new SimpleDateFormat("MMMM-dd-yyyy", Locale.US);
		String formattedDate = df.format(c.getTime());
		
		lastffup.setText(formattedDate);
		
		home.setOnClickListener(this);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btSavePatient) {
			saveRecord = new DBHelper(this);

	        try {

	                saveRecord.createDataBase();

	        } catch (IOException ioe) {

	                throw new Error("Unable to create database");

	        }

	        try {

	                saveRecord.openDataBase();

	        } catch (SQLException sqle) {

	                throw sqle;

	        }
			
			strFname = firstname.getText().toString();
			strLname = lastname.getText().toString();
			strBday = bday.getText().toString();
			strTelphone = telphone.getText().toString();
			strLastffup = lastffup.getText().toString();
			strDiagnosis = diagnosis.getText().toString();
			strBP = bp.getText().toString();
			strHeart = heart.getText().toString();
			strDiabetes = diabetes.getText().toString();
			strLung = lung.getText().toString();
			strBrain = brain.getText().toString();
			strMuscle = muscle.getText().toString();
			strAbdomen = abdomen.getText().toString();
			strUrine = urine.getText().toString();
			strGout = gout.getText().toString();
			strPrescription = prescription.getText().toString();
			
			saveRecord.insert(strFname, strLname, strBday, strTelphone, strLastffup, strDiagnosis, 
					strBP, strHeart, strDiabetes, strLung, strBrain, 
					strMuscle, strAbdomen, strUrine, strGout, strPrescription);
			Toast.makeText(this, "Patient has been added successfully!", Toast.LENGTH_SHORT).show();
			saveRecord.close();
			clearFields();
		} else if (id == R.id.btCancelPatient) {
			clearFields();
			
		} else if (id == R.id.btHome) {
			finish();
		}
	}
	
	private void clearFields(){
		firstname.setText("");
		lastname.setText("");
		bday.setText("");
		telphone.setText("");
		lastffup.setText("");
		diagnosis.setText("");
		bp.setText("");
		heart.setText("");
		lung.setText("");
		brain.setText("");
		muscle.setText("");
		abdomen.setText("");
		urine.setText("");
		gout.setText("");
		prescription.setText("");
	}
	
}
