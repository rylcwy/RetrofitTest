package com.example.wangyu.retrofittest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import static com.example.wangyu.retrofittest.BetaList.versionsList;

public class VersionAdapter extends ArrayAdapter<Versions>{
    private int resourceId;
    private final Context mContext;
    private final SparseBooleanArray mCollapsedStatus;


    public VersionAdapter(Context context, int textViewResourceId,List<Versions> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        mContext=context;
        mCollapsedStatus = new SparseBooleanArray();


    }


    @Override
    public int getCount() {
        return versionsList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.expandableTextView = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
            viewHolder.versionName=(TextView)convertView.findViewById(R.id.version_name);
            viewHolder.versionCode=(TextView)convertView.findViewById(R.id.version_code);
            viewHolder.versionDate=(TextView)convertView.findViewById(R.id.version_date);
            viewHolder.versionReporter=(TextView)convertView.findViewById(R.id.reporter);
            viewHolder.versionUpdate=(TextView)convertView.findViewById(R.id.force_update);
            viewHolder.download=(Button)convertView.findViewById(R.id.download);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.versionCode.setText(versions.getVersionCode());
        viewHolder.versionName.setText(versions.getVersionName());
        viewHolder.versionDate.setText(versions.getVersionDate());
        viewHolder.versionReporter.setText(versions.getVersionReporter());
        viewHolder.versionUpdate.setText(versions.getForceUpdate());
        viewHolder.expandableTextView.setText(versions.getVersionDetail(), mCollapsedStatus, position);


        return convertView;

    }

    private static class ViewHolder{
        ExpandableTextView expandableTextView;
        TextView versionName,versionCode,versionDate,versionReporter,versionUpdate;
        Button download;

    }

}
