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
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.w3c.dom.Text;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.versions_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.expandableTextView = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
            TextView versionName=(TextView)convertView.findViewById(R.id.version_name);
            TextView versionCode=(TextView)convertView.findViewById(R.id.version_code);
            TextView versionDate=(TextView)convertView.findViewById(R.id.version_date);
            TextView versionReporter=(TextView)convertView.findViewById(R.id.reporter);
            TextView versionUpdate=(TextView)convertView.findViewById(R.id.force_update);
            Button download=(Button)convertView.findViewById(R.id.download);
            versionCode.setText(versions.getVersionCode());
            versionName.setText(versions.getVersionName());
            versionDate.setText(versions.getVersionDate());
            versionReporter.setText(versions.getVersionReporter());
            versionUpdate.setText(versions.getForceUpdate());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.expandableTextView.setText(versions.getVersionDetail(), mCollapsedStatus, position);

        return convertView;

    }

    private static class ViewHolder{
        ExpandableTextView expandableTextView;
    }

}
