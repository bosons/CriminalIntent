package com.bignerdranch.android.criminalintent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema;

import java.util.*;

import static com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by xyang on 4/23/15.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private final SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mDatabase = new CrimeBaseHelper(context).getWritableDatabase();
        mCrimes = new ArrayList<Crime>();
        for(int i =0; i< 0; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime#"+i);
            crime.setIndex(i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        if(mCrimes.size() > 0) {
            Log.i("Eric", "mCrimes is not empty");
            return mCrimes;
        }
        Log.i("Eric", "mCrimes is empty, go to query db");
        CrimeCursorWrapper cursorWrapper = queryCrimes(null, null);

        try{
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()) {
                mCrimes.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return mCrimes;
    }

    public Crime getCrime(UUID uuid) {
        for(Crime crime: mCrimes){
            if(crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

    public void addCrime(Crime crime) {
        mCrimes.add(crime);
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }
    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString}
        );
    }

    public void removeCrime(Crime crime) {
        mCrimes.remove(crime);
        mDatabase.delete(CrimeTable.NAME,
                CrimeTable.Cols.UUID + "= ? ",
                new String[] {crime.getId().toString()}
                );
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID, crime.getId().toString());
        contentValues.put(CrimeTable.Cols.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        contentValues.put(CrimeTable.Cols.SOLVED, crime.isSolved()? 1 : 0);
        contentValues.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return contentValues;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Log.i("Eric", "in queryCrimes");
        Cursor cursor = mDatabase.query(
          CrimeTable.NAME,
          null,
          whereClause,
          whereArgs,
          null,
          null,
          null
        );
        return new CrimeCursorWrapper(cursor);
    }

}
