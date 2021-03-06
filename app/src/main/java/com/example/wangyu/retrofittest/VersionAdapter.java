package com.example.wangyu.retrofittest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

import static java.lang.Integer.parseInt;

public class VersionAdapter extends ArrayAdapter<Versions> implements View.OnClickListener{

    private int resourceId;
    private final Context mContext;
    private final SparseBooleanArray mCollapsedStatus;
    private List<Versions> versionsList;
    private DownloadService.DownloadBinder mdownloadBinder;
    private String downloadUrl;

    public VersionAdapter(Context context, int resource, List<Versions> versionsList, DownloadService.DownloadBinder downloadBinder){
        super(context, resource, versionsList);
        resourceId=resource;
        mContext=context;
        mCollapsedStatus = new SparseBooleanArray();
        this.versionsList = versionsList;
        mdownloadBinder=downloadBinder;
    }


    @Override
    public int getCount() {
        return versionsList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download:
                int ButtonPosition=(int) v.getTag(R.id.btn);
                String numberstr=StringUtils.substringBetween(versionsList.get(ButtonPosition).getApkUrl(),"apk/","/download");
                int number=Integer.parseInt(numberstr);
                Call<ResponseBody> getDownloadUrlCall=RetrofitCommunication.getRes().getDownloadUrl(number);
                getDownloadUrlCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String rawResponse=response.raw().toString();
                        downloadUrl=StringUtils.substringBetween(rawResponse,"url=","}");
                        mdownloadBinder.startDownload(downloadUrl);
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("download", "得到重定向的下载url失败"+t);
                    }
                });


        }
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
        viewHolder.download.setOnClickListener(this);
        viewHolder.download.setTag(R.id.btn,position);
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
