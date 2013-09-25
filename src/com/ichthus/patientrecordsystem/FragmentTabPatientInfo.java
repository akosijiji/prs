package com.ichthus.patientrecordsystem;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentTabPatientInfo extends SherlockFragment {

	    @Override
	    public SherlockFragmentActivity getSherlockActivity() {
	        return super.getSherlockActivity();
	    }
	   
	    @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	    }
	    
	    @Override
	     public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	     }
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        // Get the view from fragment_patient_information.xml
	        View view = inflater.inflate(R.layout.fragment_patient_information, container, false);
	        
	        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto-regular.ttf");
	        
	        Bundle b = getActivity().getIntent().getExtras();
	        
			String patientName = b.getString("patientname"); 
			String patientLName = b.getString("patientLname"); 
			String patientLastFFUp = b.getString("lastffup");
			((TextView)view.findViewById(R.id.tvPatientName)).setTypeface(tf);
			((TextView)view.findViewById(R.id.tvPatientName)).setText(patientName + " " + patientLName);
			((TextView)view.findViewById(R.id.tvPatientLastFFUp)).setText(patientLastFFUp);
	        return view;
	    }
	 
	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        setUserVisibleHint(true);
	    }
}
