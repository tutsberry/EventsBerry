package com.tutsberry.eventsberry.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tutsberry.com on 10/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */

public class Util {

    public static final String TAG = Util.class.getSimpleName();

    /**
     * Check the Internet connectivity
     * @param context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Read a URL
     * @param urlString
     * @return String
     * @throws Exception
     */
    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int resCode = conn.getResponseCode();
            Log.d(TAG, "The response is: " + resCode);

            if(resCode == 200) {
                is = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

                Log.d(TAG, "Fetching URL : " + urlString);
                return buffer.toString();
            } else {
                return "404";
            }
        } finally {
            if (reader != null)
                reader.close();
            if (is != null)
                is.close();
        }
    }

    /**
     * Write file to app data
     * @param context
     * @param filename
     * @param data
     * @return boolean
     */
    public static boolean writeFile(Context context, String filename, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d(TAG, filename + " File written.");
            return true;
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
            return false;
        }

    }

    /**
     * Read file from app data
     * @param context
     * @param filename
     * @return boolean
     */
    public static String readFile(Context context, String filename) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.d(TAG, "Read File : " + filename);
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
        return ret;
    }

    /**
     * Delete file from app data
     * @param context
     * @param filename
     * @return boolean
     */
    public static boolean deleteFile(Context context, String filename) {
        Log.d(TAG, filename + " File deleted.");
        return context.deleteFile(filename);
    }


    /**
     * Make a ProgressDialog bar with title and description
     * @param context
     * @param title
     * @param desc
     * @return ProgressDialog
     */
    public static ProgressDialog showLoading(Context context, String title, String desc ) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle(title);
        progress.setMessage(desc);
        progress.show();
        // To dismiss the dialog
        return progress;
    }
}
