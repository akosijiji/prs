package com.ichthus.patientrecordsystem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

public class Test extends Activity{
    //these are the titles that will appear on the "tabs"
final String[] page_titles = new String[]{"Personal Info", "Diagnosis", "Medications" };
//this will go the description TextView
    final String[] desc = new String[]{
        "This is the homepage the first one you will see.",
        "I'm pretty much me for now I run this really cool blog you should check it out at mycodeandlife.wordpress.com",
        "I build appps mostly for fun. If you ever want an app just holla"
};
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.simple_tabs);

    //Instantiating the adapter
    GiloAdapter mAdapter = new GiloAdapter(this);

    //instantiate the Views
    ViewPager mPager = (ViewPager)findViewById(R.id.pager);
    mPager.setAdapter(mAdapter);

    TabPageIndicator mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
    mIndicator.setViewPager(mPager);
}

private class GiloAdapter extends PagerAdapter{

    Context context;

    public GiloAdapter(Context c){
        this.context = c;
    }

    //This is the number of pages -- 3
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return page_titles.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object o) {
        // TODO Auto-generated method stub
        return v.equals(o);
    }

    //This is the title of the page that will apppear on the "tab"
    public CharSequence getPageTitle(int position) {
        return page_titles[position];
    }

    //This is where all the magic happen
    public Object instantiateItem(View pager, int position) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.main_page, null, false);

        TextView title = (TextView)v.findViewById(R.id.tvTitle);
        TextView description = (TextView) v.findViewById(R.id.tvdesc);

        title.setText(page_titles[position]);
        description.setText(desc[position]);

        //This is very important
        ( (ViewPager) pager ).addView( v, 0 );

        return v;
    }

    @Override
    public void destroyItem(View pager, int position, Object view) {
        ((ViewPager) pager).removeView((View) view);
    }

    @Override
    public void finishUpdate(View view) {
    }

    @Override
    public void restoreState(Parcelable p, ClassLoader c) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View view) {
    }

}
}
