package com.ichthus.patientrecordsystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("SdCardPath")
public class DBHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.ichthus.patientrecordsystem/databases/";
	private static String DB_NAME = "patientdb";
	static String KEY_ID = "_id";
	static String KEY_FNAME = "FirstName";
	static String KEY_LNAME = "LastName";
	static String KEY_TELNO = "TelNo";
	static String KEY_CALLPATIENT = "CallPatient";
	static String KEY_AGE = "Age";
	static String KEY_CLINIC = "Clinic";
	static String KEY_DATE = "Date";
	static String KEY_LASTFFUP = "LastFFUP";
	static String KEY_DIAGNOSIS = "Diagnosis";
	static String KEY_BP = "BP";
	static String KEY_HEART = "Heart";
	static String KEY_DIABETES = "Diabetes";
	static String KEY_LUNG = "Lung";
	static String KEY_BRAINORNERVE = "BrainOrNerve";
	static String KEY_MUSCLE = "Muscle";
	static String KEY_ABDOMEN = "Abdomen";
	static String KEY_URINEORPROSTATE = "UrineOrProstate";
	static String KEY_GOUT = "Gout";
	static String KEY_THYROIDANDVITAMINS = "ThyroidAndVitamins";
	static String KEY_VACCINATIONS = "Vaccinations";
	static String KEY_PRESCRIPTION = "Prescription";
	static String KEY_CBCBLDTYPEORCHEM = "CBCBldTypeOrChem";
	static String KEY_ECG = "ECG";
	static String KEY_CXR = "CXR";
	static String KEY_TET = "TET";
	static String KEY_UTZ = "UTZ";
	static String KEY_ECHO = "Echo";
	static String KEY_CT = "CT";
	static String KEY_MD = "MD";
	static String KEY_UPPERSPACE = "Upperspace";
	static String KEY_HEARTDM = "HeartDM";
	static String KEY_LUNGBRAIN = "LungBrain";
	static String KEY_MUSCLEABDOMEN = "MuscleAbdomen";
	static String KEY_URINEGOUT = "UrineGout";
	static String KEY_THYROID = "Thyroid";
	static String KEY_EMAIL = "Email";
	static String KEY_ANCILLARYPROCEDURE = "AncillaryProcedure";
	
	static String DB_TABLE = "tblPatient";
	private SQLiteDatabase myDataBase; 
	private final Context myContext;

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}	

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();
		
		myDataBase = null;
		
		 if (dbExist) {
		        // do nothing - database already exist
		    } else {

		        // By calling this method and empty database will be created into
		        // the default system path

		        myDataBase = this.getReadableDatabase(); 
		        myDataBase.close();
	

			try {
				copyDataBase(); 
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database doesn't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, 
				SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public Cursor getAllPatients()
	{
	    Cursor localCursor = 
	    		this.myDataBase.query(DB_TABLE, new String[] { 
	    				KEY_ID, KEY_FNAME, KEY_LNAME, KEY_DIAGNOSIS }, null, null, null, null, null);
	    if (localCursor != null)
	      localCursor.moveToFirst();
	    return localCursor;
	}
	
	
	// Add your public helper methods to access and get content from the database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
	// to you to create adapters for your views.



}