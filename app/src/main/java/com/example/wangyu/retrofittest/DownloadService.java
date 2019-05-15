package com.example.wangyu.retrofittest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUrl;
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading....", progress));

        }

        @Override
        public void onSuccess(File file) {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download sussess", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
            Intent install = new Intent(Intent.ACTION_VIEW);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                Uri uri=FileProvider.getUriForFile(MyApplication.getContext(),"com.example.wangyu.retrofittest.fileprovider",file);
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                install.setDataAndType(uri, "application/vnd.android.package-archive");
            }
            else {
                install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            }
            startActivity(install);

        }

        @Override
        public void onFail() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Fail", -1));
            Toast.makeText(DownloadService.this, "Download Fail", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Canceled", Toast.LENGTH_SHORT).show();

        }
    };

    private DownloadBinder mBinder=new DownloadBinder();


    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if (downloadTask==null){
                downloadUrl=url;
                downloadTask=new DownloadTask(downloadListener);
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("Downloading",0));
                Toast.makeText(DownloadService.this,"Download...",Toast.LENGTH_SHORT).show();
            }
        }
        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if (downloadTask!=null){
                downloadTask.cancelDownload();
            }
            else {
                if (downloadUrl !=null){
                    String fileName=StringUtils.substringAfter(downloadUrl,"https://assets.sfcdn.org/pub2/");
                    String dircetory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file=new File(dircetory+fileName);
                    if (file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, ListActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String ChannelID = "com.example.wangu.retroffittest";
            NotificationChannel mChannel = new NotificationChannel(ChannelID, "下载下载", NotificationManager.IMPORTANCE_DEFAULT);
            getNotificationManager().createNotificationChannel(mChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MyApplication.getContext(), ChannelID);
            builder.setContentIntent(pi);
            builder.setContentTitle(title);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            if (progress > 0) {
                builder.setContentText(progress + "%");
                builder.setProgress(100, progress, false);
            }
            return builder.build();
        }
        else {
            NotificationCompat.Builder builder=new NotificationCompat.Builder(MyApplication.getContext(),"default");
            return builder.build();
        }

    }
}
