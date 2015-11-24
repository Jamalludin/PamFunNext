package com.android.jamalludin.pamfunnext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jamal on 24/11/15.
 */
public class NavListAdapter extends ArrayAdapter<NavItem> {

    Context context;
    int resLayout;
    List<NavItem> navItemList;

    public NavListAdapter(Context context, int resLayout, List<NavItem> navItemList) {
        super(context, resLayout, navItemList);

        this.context = context;
        this.resLayout = resLayout;
        this.navItemList = navItemList;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, resLayout, null);

        TextView tvTitle = (TextView)view.findViewById(R.id.title1);
        TextView tvsubTitle = (TextView)view.findViewById(R.id.subtitle);
        ImageView navIcon = (ImageView)view.findViewById(R.id.nav_icon);

        NavItem navItem = navItemList.get(position);

        tvTitle.setText(navItem.getTitle());
        tvsubTitle.setText(navItem.getSubTitle());
        navIcon.setImageResource(navItem.getResIcon());

        return view;
    }
}
