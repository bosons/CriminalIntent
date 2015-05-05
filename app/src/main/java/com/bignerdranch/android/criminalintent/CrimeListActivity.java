package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by xyang on 4/23/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Eric", "CrimeListActivity onDestroy");
    }
}

