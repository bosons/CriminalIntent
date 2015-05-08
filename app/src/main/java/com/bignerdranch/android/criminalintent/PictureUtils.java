package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by xyang on 5/7/15.
 */
public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //to get src image parameters
        BitmapFactory.decodeFile(path, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Figure out how much to scale down
        int inSampleSize = 1;
        if(srcWidth > destWidth || srcHeight > destHeight) {
            int heightScaleDown = Math.round(srcHeight/destHeight);
            int widthScaleDown = Math.round(srcWidth/destWidth);
            inSampleSize = Math.max(heightScaleDown, widthScaleDown);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //read in and create final bitmap
        return BitmapFactory.decodeFile(path, options);
    }

    /* get activity screen size and scale down,
     *so picture resolution is larger than screen resolution
     */
    public static Bitmap getScaledBitmap(String path, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
}
