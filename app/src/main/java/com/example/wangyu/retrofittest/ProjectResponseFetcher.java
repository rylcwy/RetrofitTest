package com.example.wangyu.retrofittest;

import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;

public interface ProjectResponseFetcher extends Serializable {
    public Call<ResponseBody> getCallableResponse(int pageNumber);
}
