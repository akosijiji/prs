package com.ichthus.patientrecordsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentTabDiagnosis extends SherlockFragment {
 
    @Override
    public SherlockFragmentActivity getSherlockActivity() {
        return super.getSherlockActivity();
    }
 
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Get the view from fragment_patient_diagnosis.xml
        View view = inflater.inflate(R.layout.fragment_patient_diagnosis, container, false);
        
        Bundle b = getActivity().getIntent().getExtras();
        
        String patientDiagnosis = b.getString("diagnosis");
		((TextView)view.findViewById(R.id.tvDiagnosis)).setText(patientDiagnosis);
        
        return view;
    }
 
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
}
