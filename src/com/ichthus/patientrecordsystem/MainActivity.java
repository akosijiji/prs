package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	Button viewallpatients, addpatient, home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initControls();
	}

	private void initControls() {
		// TODO Auto-generated method stub
		viewallpatients = (Button) findViewById (R.id.btViewAllPatients);
		addpatient = (Button) findViewById (R.id.btAddPatient);

		viewallpatients.setOnClickListener(this);
		addpatient.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btViewAllPatients) {
			Intent view = new Intent(this, Test.class);
			startActivity(view);
		} else if (id == R.id.btAddPatient) {
			Intent add = new Intent(this, ViewPatient.class);
			startActivity(add);
		} 
	}

}
