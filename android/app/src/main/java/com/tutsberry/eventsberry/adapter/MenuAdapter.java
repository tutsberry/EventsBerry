package com.tutsberry.eventsberry.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tutsberry.eventsberry.R;
import com.tutsberry.eventsberry.model.Event;
import com.tutsberry.eventsberry.model.Page;
import com.tutsberry.eventsberry.util.FontView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by tutsberry.com on 16/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */
public class MenuAdapter extends BaseAdapter {

    protected List<Page> pages;
    protected Context mContext;
    protected JSONObject themeColors;

    public MenuAdapter(Context context, Event event) {
        this.pages = event.getPages();
        this.mContext = context;
        this.themeColors = event.getTheme();

    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Object getItem(int position) {
        return pages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        IconListHolder viewHolder;

        if( convertView == null ) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.icon, null);
            viewHolder = new IconListHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (IconListHolder) v.getTag();
        }

        //Set the text and Icon
        viewHolder.textViewIcon.setText(Html.fromHtml(pages.get(position).getIcon()).toString());
        viewHolder.textViewName.setText(pages.get(position).getTitle());

        try {
            viewHolder.textViewIcon.setTextColor(Color.parseColor(themeColors.getString("color_primary_dark")));
            viewHolder.textViewName.setTextColor(Color.parseColor(themeColors.getString("color_primary_accent")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    private class IconListHolder {
        public FontView textViewIcon;
        public TextView textViewName;

        public IconListHolder(View base) {
            textViewIcon = (FontView) base.findViewById(R.id.iconView);
            textViewName = (TextView) base.findViewById(R.id.iconTextView);
        }
    }
}
