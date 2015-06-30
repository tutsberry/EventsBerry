package com.tutsberry.eventsberry;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tutsberry.eventsberry.Service.DataService;
import com.tutsberry.eventsberry.Service.TaskListener;
import com.tutsberry.eventsberry.adapter.MenuAdapter;
import com.tutsberry.eventsberry.model.Event;
import com.tutsberry.eventsberry.model.Page;
import com.tutsberry.eventsberry.util.Setting;
import com.tutsberry.eventsberry.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class DashboardActivity extends ActionBarActivity implements TaskListener {

    public static final String TAG = DashboardActivity.class.getSimpleName();
    public static final String CURRENT_EVENT = "CURRENT_EVENT";

    private Event event;
    private String eventCode;
    private Setting appSetting;

    protected ImageView appIcon;
    protected TextView appTitle;
    protected LinearLayout appHeaderLayout;

    private MenuAdapter menuAdapter;
    private JSONObject themeColors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        appSetting = new Setting(this);

        //Check for event already initialized
        eventCode = appSetting.getString(CURRENT_EVENT, null);

        if(eventCode == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //Open the event code file
            String stringJson = Util.readFile(this, eventCode + ".txt");

            event = new EventJSONParser(stringJson).parse();

            //Setup Event Theme
            setupEventTheme(event);

            //IconGrid view
            GridView gridView = (GridView) findViewById(R.id.iconGrid);
            menuAdapter = new MenuAdapter(this, event);
            gridView.setAdapter(menuAdapter);

            //Handle the event on GridView
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Page currentPage = (Page) menuAdapter.getItem(position);
                    //Toast.makeText(DashboardActivity.this, currentPage.getTitle(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DashboardActivity.this, WebViewActivity.class);
                    intent.putExtra("PAGE_TITLE", currentPage.getTitle());
                    intent.putExtra("PAGE_BODY", currentPage.getBody());
                    intent.putExtra("PAGE_ICON", currentPage.getIcon());
                    intent.putExtra("EVENT_PARAMS", event.getTheme().toString());
                    try {
                        intent.putExtra("THEME_PRIMARY", themeColors.getString("color_primary"));
                        intent.putExtra("APP_LOGO", themeColors.getString("logo"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    startActivity(intent);
                }
            });
        }
    }

    private void setupEventTheme(Event event) {
        //Event Theme Colors
        themeColors = event.getTheme();

        //Setup the header of App with Title and Logo
        appHeaderLayout = (LinearLayout) findViewById(R.id.include);
        appIcon = (ImageView) findViewById(R.id.appTitleLogo);
        appTitle = (TextView) findViewById(R.id.appTitle);

        //Theme Apply
        View root = appTitle.getRootView();
        try {
            //Background color
            root.setBackgroundColor(Color.parseColor(themeColors.getString("bg_color")));

            //Header Colors
            appHeaderLayout.setBackgroundColor(Color.parseColor(themeColors.getString("color_primary")));
            appTitle.setTextColor(Color.parseColor("#FFFFFF"));

            //Load the logo with Picasso
            Picasso.with(this)
                    .load(themeColors.getString("logo"))
                    .placeholder(R.drawable.logo_placeholder)
                    .into(appIcon);

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

        appTitle.setText(event.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            appSetting.removeString(CURRENT_EVENT);
            Util.deleteFile(this, eventCode + ".txt");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.action_refresh) {
            //Refresh the Data
            new DataService(appSetting).fetch(this, eventCode, this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskDone(String result) {
        if(result != "400") {
            //Reload the activity
            finish();
            startActivity(getIntent());
        }
    }
}
