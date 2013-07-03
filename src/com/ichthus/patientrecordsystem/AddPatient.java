package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddPatient extends Activity implements OnClickListener {
	
	Button save, cancel;
	EditText firstname, lastname, bday, telphone, lastffup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.addpatient);
		
		initControls();
		
	}

	private void initControls() {
		// TODO Auto-generated method stub
		save = (Button) findViewById (R.id.btSavePatient);
		cancel = (Button) findViewById (R.id.btCancelPatient);
		
		
		firstname = (EditText) findViewById (R.id.etFirstname);
		lastname = (EditText) findViewById (R.id.etLastname);
		bday = (EditText) findViewById (R.id.etBday);
		telphone = (EditText) findViewById (R.id.etLastFFUp);
		
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btSavePatient) {
		} else if (id == R.id.btCancelPatient) {
		}
	}
	
}
