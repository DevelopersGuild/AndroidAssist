package com.assist.dg.androidassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hendryjoe on 10/10/15.
 */
public class CourseListAdapter extends BaseAdapter {

    private ArrayList<RequiredClass> objects;
    private LayoutInflater inflater;

    private class ViewHolder{
        TextView requiredClass;
        TextView univEquivalent;
    }

    public CourseListAdapter(Context context, ArrayList<RequiredClass> objects){
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.course_list, null);
            holder.requiredClass = (TextView) convertView.findViewById(R.id.required_class_textview);
            holder.univEquivalent = (TextView) convertView.findViewById(R.id.univ_equivalent_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.requiredClass.setText(objects.get(position).getFormalClassName());
        holder.univEquivalent.setText(objects.get(position).getUnivEquivalent());

        return convertView;
    }
}
