package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ViewPatient extends Activity implements OnClickListener {
	
	Button personalInfo, diagnosis, medications, prescription, ancillaryprocedure, home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Sets the Layout
		setContentView(R.layout.viewpatient);
		
		// Call the widgets please
		initControls();
		
	}

	private void initControls() {
		// TODO Auto-generated method stub
		personalInfo = (Button) findViewById(R.id.btViewPersonalInfo);
		diagnosis = (Button) findViewById (R.id.btViewDiagnosis);
		medications = (Button) findViewById (R.id.btViewMedications);
		prescription = (Button) findViewById (R.id.btViewPrescription);
		ancillaryprocedure = (Button) findViewById (R.id.btViewAncillaryProcedure);
		home = (Button) findViewById (R.id.btHome);
		
		personalInfo.setOnClickListener(this);
		diagnosis.setOnClickListener(this);
		medications.setOnClickListener(this);
		prescription.setOnClickListener(this);
		ancillaryprocedure.setOnClickListener(this);
		home.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btViewPersonalInfo:
			break;
		case R.id.btViewDiagnosis:
			break;
		case R.id.btViewMedications:
			break;
		case R.id.btViewAncillaryProcedure:
			break;
		case R.id.btViewPrescription:
			break;
		case R.id.btHome:
			finish();
		}
	}

}
