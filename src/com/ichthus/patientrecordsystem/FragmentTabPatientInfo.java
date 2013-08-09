package com.ichthus.patientrecordsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentTabPatientInfo extends SherlockFragment {
	
	//TextView name, bday, age, telphone, tvdiagnosis, lastffup;

		 
	    @Override
	    public SherlockFragmentActivity getSherlockActivity() {
	        return super.getSherlockActivity();
	        
	        //displayPatientRecord();
	    }
	    /*
	    private void displayPatientRecord() {
			// TODO Auto-generated method stub
	    	
	    	name = (TextView) findViewById (R.id.tvPatientName);
			bday = (TextView) findViewById (R.id.tvPatientBday);
			age = (TextView) findViewById (R.id.tvPatientAge);
			telphone = (TextView) findViewById (R.id.tvPatientTelPhone);
			tvdiagnosis = (TextView) findViewById (R.id.tvDiagnosis);
			lastffup = (TextView) findViewById (R.id.tvPatientLastFFUp);
	    	
			Intent i = getIntent();
			String patientName = i.getStringExtra("patientname");
			String patientLastFFUp = i.getStringExtra("lastffup");
			String patientDiagnosis = i.getStringExtra("diagnosis");
			name.setText(patientName);
			lastffup.setText(patientLastFFUp);
			tvdiagnosis.setText(patientDiagnosis);	        
		} */
	 
	    @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	    }
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        // Get the view from fragmenttab1.xml
	        View view = inflater.inflate(R.layout.fragment_patient_information, container, false);
	        return view;
	    }
	 
	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        setUserVisibleHint(true);
	    }
}
