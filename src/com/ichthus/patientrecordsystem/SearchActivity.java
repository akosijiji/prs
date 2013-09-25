package com.ichthus.patientrecordsystem;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class SearchActivity extends SherlockFragmentActivity {

	/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       Log.d("Hello", "This is the search view activity");
       // TODO Auto-generated method stub
       super.onCreate(savedInstanceState);
       setContentView(R.layout.search_result_layout);
            }


    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
        searcdhQuery = intent.getStringExtra(SearchManager.QUERY);
        //here we shall do e search..
        Log.d(TAG, "This is the search query:" + searcdhQuery);

                    //This is the asynctask query to connect to the database...
        String[] value = {searcdhQuery};
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.execute(value);
        }
        }

            @Override
        protected void onNewIntent(Intent intent) {
         // TODO Auto-generated method stub
         super.onNewIntent(intent);
         handleIntent(intent);
        } */
}
