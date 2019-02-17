package com.example.wangyu.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;

public interface ProjectResponseFetcher {
    public Call<ResponseBody> getCallableResponse();
}
