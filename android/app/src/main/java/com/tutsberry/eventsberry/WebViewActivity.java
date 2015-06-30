package com.tutsberry.eventsberry;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class WebViewActivity extends ActionBarActivity {

    private WebView webView;
    private String pageTitle;
    private String pageIcon;
    private String pageBody;
    private String themePrimaryColor;
    private String appLogoURL;
    private JSONObject themeColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //Get the data from Intent
        Intent intent = getIntent();
        pageTitle = intent.getStringExtra("PAGE_TITLE");
        pageIcon = intent.getStringExtra("PAGE_ICON");
        pageBody = intent.getStringExtra("PAGE_BODY");
        appLogoURL = intent.getStringExtra("APP_LOGO");

        try {
            themeColors = new JSONObject(intent.getStringExtra("EVENT_PARAMS"));
            themePrimaryColor = themeColors.getString("color_primary");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Push html to WebView
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadData(buildHTMLPage(pageBody), "text/html", "UTF-8");

        //Setup the header of App with Title and Logo
        LinearLayout appHeaderLayout = (LinearLayout) findViewById(R.id.include);
        ImageView appIcon = (ImageView) findViewById(R.id.appTitleLogo);
        TextView appTitle = (TextView) findViewById(R.id.appTitle);

        //Header Colors
        appHeaderLayout.setBackgroundColor(Color.parseColor(themePrimaryColor));
        appTitle.setText(pageTitle);
        appTitle.setTextColor(Color.parseColor("#FFFFFF"));

        //Load the logo with Picasso
        Picasso.with(this)
                .load(appLogoURL)
                .placeholder(R.drawable.logo_placeholder)
                .into(appIcon);
    }

    private String buildHTMLPage(String pageBody) {
        String accentColor = "" , textPrimary = "";
        try {
            accentColor = themeColors.getString("color_primary_accent");
            textPrimary = themeColors.getString("text_primary");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "    <style>" +
                "    body {font-family:arial; color:#666; padding:8px}" +
                "    h1, h2, h3, h4, h5, p {margin-top:0}" +
                "    h1, h2, h3 {color: " + textPrimary + "}" +
                "    a {color: " + accentColor + " }" +
                "    </style>" +
                " </head>" +
                "<body>" +
                pageBody +
                "</body>" +
                "</html>";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
