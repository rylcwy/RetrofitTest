package com.example.wangyu.retrofittest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

public class VersionAdapter extends ArrayAdapter<Versions>{
    private int resourceId;
    private final Context mContext;
    private final SparseBooleanArray mCollapsedStatus;
    private final String[] sampleStrings;

    public VersionAdapter(Context context, int textViewResourceId,List<Versions> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        mContext=context;
        mCollapsedStatus = new SparseBooleanArray();
        sampleStrings = mContext.getResources().getStringArray(R.array.sampleStrings);





    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Versions versions=getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.versions_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.expandableTextView = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
            TextView versionName=(TextView)convertView.findViewById(R.id.version_name);
            TextView versionCode=(TextView)convertView.findViewById(R.id.version_code);
            TextView versionDate=(TextView)convertView.findViewById(R.id.version_date);
            Button download=(Button)convertView.findViewById(R.id.download);
            versionCode.setText(versions.getVersionCode());
            versionName.setText(versions.getVersionName());
            versionDate.setText(versions.getVersionDate());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.expandableTextView.setText(sampleStrings[position], mCollapsedStatus, position);


        return convertView;

    }

    private static class ViewHolder{
        ExpandableTextView expandableTextView;
    }

}
