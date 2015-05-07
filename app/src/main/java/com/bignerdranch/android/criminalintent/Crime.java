package com.bignerdranch.android.criminalintent;
import android.widget.DatePicker;

import java.util.*;

/**
 * Created by xyang on 4/21/15.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private int mIndex;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public Crime() {

        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public Crime(UUID uuid) {
        mId = uuid;
        mDate = new Date();
    }

    public String getPhotoFileName() {
        return "IMG_" + getId().toString() + ".jpg";
    }

}
