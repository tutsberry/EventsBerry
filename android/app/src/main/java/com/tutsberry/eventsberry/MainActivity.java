package com.tutsberry.eventsberry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tutsberry.eventsberry.Service.DataService;
import com.tutsberry.eventsberry.Service.TaskListener;
import com.tutsberry.eventsberry.util.Setting;

public class
        MainActivity extends ActionBarActivity implements TaskListener {

    private Setting appSetting;
    protected EditText editTextEventCode;
    private String eventCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEventCode = (EditText) findViewById(R.id.editTextEventCode);
        appSetting = new Setting(this);

        //Check for event already initialized
        if(appSetting.getString("CURRENT_EVENT", null) != null) {
            startDashboard();
        }
    }

    private void startDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void getEvent(View view) throws Exception {
        //Get the event code and validate
        eventCode = editTextEventCode.getText().toString();
        if(! eventCode.isEmpty()) {
            //get the event by event code
            new DataService(appSetting).fetch(this, eventCode, this);
            return;
        }else {
            Toast.makeText(this, "Event Code is required.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTaskDone(String result) {
        startDashboard();
    }
}
