package com.tutsberry.eventsberry.Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.tutsberry.eventsberry.util.Setting;
import com.tutsberry.eventsberry.util.Util;

/**
 * Created by tutsberry.com on 19/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */
public class DataService {
    public static final String API_URL = "http://demo.tutsberry.com/events-berry/";
    public static final String TAG = DataService.class.getSimpleName();
    public static final String CURRENT_EVENT = "CURRENT_EVENT";
    private Setting appSetting;

    public DataService(Setting appSetting) {
        this.appSetting = appSetting;
    }

    public void fetch(Context context, String eventCode, TaskListener listener) {

        //Check the net connection
        if( ! Util.isNetworkAvailable(context) ) {
            Toast.makeText(context, "No internet connection.", Toast.LENGTH_LONG).show();
            return;
        }

        new GetEventTask(context, eventCode, listener)
                .execute(API_URL + eventCode.toUpperCase() + ".json"); //Added .json for demo
    }

    private class GetEventTask extends AsyncTask<String, Void, String> {

        private Context mContext;
        private ProgressDialog dialog;
        private String eventCode;
        private TaskListener listener;

        public GetEventTask(Context mContext, String eventCode, TaskListener listener) {
            this.mContext = mContext;
            this.eventCode = eventCode;
            this.listener = listener;
            dialog = Util.showLoading(mContext, "Loading...", "Please wait...");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return Util.readUrl(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            //Assign the returned json to string
            if(s != "404") {
                Log.d(TAG, s);
                //Save returned json as file
                if( Util.writeFile(mContext, eventCode + ".txt", s) ) {
                    //Save current Event Code in SharedPref
                    appSetting.setString(CURRENT_EVENT, eventCode);
                    Toast.makeText(mContext, "Event code " + eventCode + " loaded.", Toast.LENGTH_SHORT).show();
                    listener.onTaskDone(s);
                }
            }else{
                Toast.makeText(mContext, "Event with " + eventCode + " not available ", Toast.LENGTH_LONG).show();
            }
        }
    }
}
