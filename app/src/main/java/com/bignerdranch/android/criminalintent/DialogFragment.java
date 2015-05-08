package com.bignerdranch.android.criminalintent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

/**
 * Created by xyang on 5/7/15.
 */
public class DialogFragment extends Fragment {
    private File mPhotoFile;
    private ImageView mPhotoView;

    public static DialogFragment newInstance(UUID crime_id) {
        Bundle args = new Bundle();
        args.putSerializable(CrimeFragment.ARG_CRIME_ID, crime_id);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crime_id = (UUID)getArguments().getSerializable(CrimeFragment.ARG_CRIME_ID);
        Crime crime= CrimeLab.get(getActivity()).getCrime(crime_id);
        Log.i("Eric", "DialogFragment onCreate with crime_id" + crime_id.toString());
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(crime);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        mPhotoView = (ImageView)v.findViewById(R.id.full_screen_crime_photo);
        //inflate mPhotoView
        Log.i("Eric", "DialogFragment onCreateView");
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }

        return v;
    }
}
