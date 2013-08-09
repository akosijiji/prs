package com.ichthus.patientrecordsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ViewPatient extends SherlockFragmentActivity {
	
	// Declare Variables
    ActionBar mActionBar;
    ViewPager mPager;
    Tab tab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Sets the Layout
		setContentView(R.layout.view_patient);
		
		passActivityResult();
		
		// Activate Navigation Mode Tabs
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Locate ViewPager in activity_main.xml
        mPager = (ViewPager) findViewById(R.id.pager);
 
        // Activate Fragment Manager
        FragmentManager fm = getSupportFragmentManager();
 
        // Capture ViewPager page swipes
        ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Find the ViewPager Position
                mActionBar.setSelectedNavigationItem(position);
            }
        };
 
        mPager.setOnPageChangeListener(ViewPagerListener);
        // Locate the adapter class called ViewPagerAdapter.java
        ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(fm);
        // Set the View Pager Adapter into ViewPager
        mPager.setAdapter(viewpageradapter);
 
        // Capture tab button clicks
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
 
            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                // Pass the position on tab click to ViewPager
                mPager.setCurrentItem(tab.getPosition());
            }
 
            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
 
            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
        };
 
        // Create first Tab
        tab = mActionBar.newTab().setText("Patient Info").setTabListener(tabListener);
        mActionBar.addTab(tab);
 
        // Create second Tab
        tab = mActionBar.newTab().setText("Diagnosis").setTabListener(tabListener);
        mActionBar.addTab(tab);
 
        // Create third Tab
        tab = mActionBar.newTab().setText("Medical Info").setTabListener(tabListener);
        mActionBar.addTab(tab);
 	
	}

	private void passActivityResult() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		String patientName = i.getStringExtra("patientname");
		String patientLastFFUp = i.getStringExtra("lastffup");
		String patientDiagnosis = i.getStringExtra("diagnosis");
		//name.setText(patientName);
		//lastffup.setText(patientLastFFUp);
		//tvdiagnosis.setText(patientDiagnosis);
		
		Intent in = new Intent(this, FragmentTabPatientInfo.class);
		in.putExtra("patientname", patientName);
		in.putExtra("lastffup", patientLastFFUp);
		in.putExtra("diagnosis", patientDiagnosis);
		startActivityForResult(i, 1);
	}

	/*
	private void displayPatientRecord() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		String patientName = i.getStringExtra("patientname");
		String patientLastFFUp = i.getStringExtra("lastffup");
		String patientDiagnosis = i.getStringExtra("diagnosis");
		name.setText(patientName);
		lastffup.setText(patientLastFFUp);
		tvdiagnosis.setText(patientDiagnosis);	        
	} */
	
	


}
