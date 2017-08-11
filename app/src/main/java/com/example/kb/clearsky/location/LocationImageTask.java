package com.example.kb.clearsky.location;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Karol on 2017-08-10.
 */

public class LocationImageTask extends AsyncTask<URL, Void, Bitmap> {

    private static final String LOG_TAG = LocationImageTask.class.getSimpleName();
    private ImageView mImageView;

    public LocationImageTask(ImageView view) {
        mImageView = view;
    }

    @Override
    protected Bitmap doInBackground(URL... urls) {
        URL imageUrl = urls[0];
        Bitmap imageBitmap = null;
        try {
            InputStream in = imageUrl.openStream();
            imageBitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
        return imageBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}
