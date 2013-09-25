package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	GridView gridview;
	
	static final String[] TITLES = {
		"Sample", 
		"Add Patient",
		"View Patients",
		"Update Patient"
	};
	
	static Integer imageIDs[] = {
			R.drawable.medical_record,
			R.drawable.medicine,
			R.drawable.medical_record,
			R.drawable.medicine
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	       					Intent update = new Intent(MainActivity.this, UpdatePatient.class);
	       					startActivity(update);
	       					break;
	       			} 
	           }
	       });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		/*
		// Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName())); */
		
		return true;
	}

	/*
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
	    
	    } */
	
	private static class ImageAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflater;
        public ImageAdapter(Context context){
            mLayoutInflater=LayoutInflater.from(context);
        }
       
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageIDs.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            ViewHolder mVHolder;
            if(converView == null){
                converView = mLayoutInflater.inflate(R.layout.custom_gridview, parent, false);
                mVHolder = new ViewHolder();
                mVHolder.mImageView = (ImageView)converView.findViewById(R.id.imgview);
                mVHolder.mTextView = (TextView)converView.findViewById(R.id.text);
                mVHolder.mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                mVHolder.mImageView.setPadding(8,8,8,8);
                converView.setTag(mVHolder);
            }else{
                mVHolder = (ViewHolder)converView.getTag();
            }
            mVHolder.mImageView.setImageResource(imageIDs[position]);
            mVHolder.mTextView.setText(TITLES[position]);
           
            return converView;
        }
       
    }
   
    static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }

}
