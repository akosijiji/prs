package com.ichthus.patientrecordsystem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	 
    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 3;
 
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
 
        // Open FragmentTab1.java
        case 0:
            FragmentTabPatientInfo fragmenttab1 = new FragmentTabPatientInfo();
            return fragmenttab1;
 
        // Open FragmentTab2.java
        case 1:
            FragmentTabDiagnosis fragmenttab2 = new FragmentTabDiagnosis();
            return fragmenttab2;
 
        // Open FragmentTab3.java
        case 2:
            FragmentTabMedical fragmenttab3 = new FragmentTabMedical();
            return fragmenttab3;
        }
        return null;
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return PAGE_COUNT;
    }
}
