package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	GridView gridview;
	
	Integer imageIDs[] = {
			R.drawable.medical_record,
			R.drawable.medicine,
			R.drawable.medical_record,
			R.drawable.medicine
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initControls();
		
	}

	private void initControls() {
		// TODO Auto-generated method stub
		
		gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	             
	       gridview.setOnItemClickListener(new OnItemClickListener()
	       {
	           public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	           {
	       			switch(position){
	       				case 0:
	       					Intent view = new Intent(MainActivity.this, Test.class);
	       					startActivity(view);
	       					break;
	       				case 1:
	       					Intent add = new Intent(MainActivity.this, AddPatient.class);
	       					startActivity(add);
	       					break;
	       				case 2:
	       					Intent viewall = new Intent(MainActivity.this, ViewAllPatients.class);
	       					startActivity(viewall);
	       					break;
	       				case 3:
	       					Intent test = new Intent(MainActivity.this, ViewPatient.class);
	       					startActivity(test);
	       					break;
	       			} 
	           }
	       });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ImageAdapter extends BaseAdapter
	{
	    private Context context;
	    
	    public ImageAdapter(Context c)
	    {
	        context = c;
	    }
	    
	    
	   // Returns the number of images
	    public int getCount(){
	     return imageIDs.length;
	    }
	    
	    // Returns the ID of an item
	    public Object getItem(int position) {
	        return position;
	    }
	    
	    // Returns the ID of an item
	    public long getItemId(int position){
	        return position;
	    }
	    
	    // Returns an ImageView View
	    public View getView(int position, View convertView, ViewGroup parent){
	        ImageView imageView;
	        if(convertView == null){
	            imageView = new ImageView(context);
	            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(5, 5, 5, 5);
	        } 
	        else{
	            imageView = (ImageView) convertView;
	        }
	        
	        imageView.setImageResource(imageIDs[position]);
	        return imageView;
	    }
	    
	    }

}
