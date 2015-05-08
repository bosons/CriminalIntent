package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.*;

/**
 * Created by xyang on 4/25/15.
 */
public class CrimePagerActivity extends ActionBarActivity {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    public static final String EXTRA_CRIME_ID = "crime_id";

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);
        mViewPager.setOffscreenPageLimit(2); //2 on each side
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }
            @Override
            public int getCount() {
                return mCrimes.size();
            }

        });
        //for set current view
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i =0; i< mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
