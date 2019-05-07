package com.example.wangyu.retrofittest;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
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

public class VersionAdapter extends ArrayAdapter<Versions>{
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private int resourceId;
    private final Context mContext;
    private final SparseBooleanArray mCollapsedStatus;
    private List<Versions> versionsList;

    public VersionAdapter(Context context, int resource, List<Versions> versionsList){
        super(context, resource, versionsList);
        resourceId=resource;
        mContext=context;
        mCollapsedStatus = new SparseBooleanArray();
        this.versionsList = versionsList;

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
        viewHolder.versionReporter.setText(versions.getversionPublisher());
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
