package com.tutsberry.eventsberry;

import android.util.Log;

import com.tutsberry.eventsberry.model.Event;
import com.tutsberry.eventsberry.model.Page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tutsberry.com on 16/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */
public class EventJSONParser {

    public static final String TAG = EventJSONParser.class.getSimpleName();

    private static final String TAG_ID = "id";
    private static final String TAG_CODE = "code";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_STARTS = "starts";
    private static final String TAG_ENDS = "ends";
    private static final String TAG_VENUE = "venue";
    private static final String TAG_PARAM = "param";
    private static final String TAG_CREATED = "created_at";
    private static final String TAG_UPDATED = "updated_at";
    private static final String TAG_PAGES = "pages";
    private static final String TAG_PAGE_ID = "id";
    private static final String TAG_PAGE_TITLE = "title";
    private static final String TAG_PAGE_ICON = "icon";
    private static final String TAG_PAGE_BODY = "body";

    private List<Page> pageList = new ArrayList<>();
    private String jsonString;

    public EventJSONParser(String jsonString) {
        this.jsonString = jsonString;
    }

    public Event parse() {

        if (this.jsonString == null) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(this.jsonString);

            //Create event and assign the values
            Event event = new Event();
            event.setId(jsonObject.getInt(TAG_ID));
            event.setCode(jsonObject.getString(TAG_CODE));
            event.setName(jsonObject.getString(TAG_NAME));
            event.setDescription(jsonObject.getString(TAG_DESC));
            event.setStarts(jsonObject.getString(TAG_STARTS));
            event.setEnds(jsonObject.getString(TAG_ENDS));
            event.setVenue(jsonObject.getString(TAG_VENUE));
            event.setCreatedAt(jsonObject.getString(TAG_CREATED));
            event.setUpdatedAt(jsonObject.getString(TAG_UPDATED));

            //Event Params
            event.setTheme(jsonObject.getJSONObject(TAG_PARAM));

            //Event Pages
            JSONArray pages = jsonObject.getJSONArray(TAG_PAGES);

            for (int i = 0; i < pages.length(); i++) {
                //Create a new Page
                JSONObject pageJsonObj = pages.getJSONObject(i);

                Page page = new Page(
                        pageJsonObj.getInt(TAG_PAGE_ID),
                        pageJsonObj.getString(TAG_PAGE_TITLE),
                        pageJsonObj.getString(TAG_PAGE_ICON),
                        pageJsonObj.getString(TAG_PAGE_BODY),
                        pageJsonObj.getString(TAG_CREATED),
                        pageJsonObj.getString(TAG_UPDATED)
                );

                pageList.add(page);
            }

            //Finally add pages to Event and return it
            event.setPages(pageList);

            return event;

        } catch (JSONException e) {
            Log.e(TAG, "Event JSON Parse error");
            Log.e(TAG, e.toString());
        }

        return null;
    }
}
