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
public class FullScreenPhotoFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private File mPhotoFile;
    private ImageView mPhotoView;

    public static FullScreenPhotoFragment newInstance(UUID crime_id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crime_id);
        FullScreenPhotoFragment fragment = new FullScreenPhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crime_id = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        Crime crime= CrimeLab.get(getActivity()).getCrime(crime_id);
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(crime);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        mPhotoView = (ImageView)v.findViewById(R.id.full_screen_crime_photo);
        //inflate mPhotoView

        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }

        return v;
    }
}
