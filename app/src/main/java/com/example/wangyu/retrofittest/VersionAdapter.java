package com.example.wangyu.retrofittest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class VersionAdapter extends ArrayAdapter<Versions>{
    private int resourceId;

    public VersionAdapter(Context context, int textViewResourceId,List<Versions> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Versions versions=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView versionName=(TextView)view.findViewById(R.id.version_name);
        TextView versionCode=(TextView)view.findViewById(R.id.version_code);
        TextView versionDate=(TextView)view.findViewById(R.id.version_date);
        TextView versionDetail=(TextView)view.findViewById(R.id.version_detail);
        Button download=(Button)view.findViewById(R.id.download);
        versionCode.setText(versions.getVersionCode());
        versionName.setText(versions.getVersionName());
        versionDetail.setText(versions.getVersionDetail());
        versionDate.setText(versions.getVersionDate());
        return view;
    }
}
