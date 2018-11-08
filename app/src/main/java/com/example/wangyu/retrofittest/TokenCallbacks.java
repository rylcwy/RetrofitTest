package com.example.wangyu.retrofittest;

import android.support.annotation.NonNull;

public interface TokenCallbacks {
    void onSuccess(@NonNull String token);
    void onFailure(@NonNull Throwable throwable);
}
