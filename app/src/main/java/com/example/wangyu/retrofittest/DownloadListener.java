package com.example.wangyu.retrofittest;

import java.io.File;

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess(File file);
    void onFail();
    void onPause();
    void onCanceled();
}
