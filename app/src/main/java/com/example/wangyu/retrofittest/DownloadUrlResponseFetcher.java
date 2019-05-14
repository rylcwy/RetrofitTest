package com.example.wangyu.retrofittest;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Response;

public interface DownloadUrlResponseFetcher extends Serializable {
    public Call<Response> getCallableResponse(int Number);
}
