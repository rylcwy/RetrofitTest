package com.example.wangyu.retrofittest;

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFail();
    void onPause();
    void onCanceled();
}
