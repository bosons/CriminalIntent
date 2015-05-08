package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.UUID;

/**
 * Created by xyang on 5/7/15.
 */
public class DialogActivity extends ActionBarActivity {
    private static final String EXTRA_CRIME_ID = "crime_id";

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context,DialogActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            Log.i("Eric", "Fragment is null at Dialog Activity call");
            UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
            fragment = com.bignerdranch.android.criminalintent.DialogFragment.newInstance(crimeId);
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
