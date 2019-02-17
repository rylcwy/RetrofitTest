package com.example.wangyu.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class TapTapProjectFetcher implements ProjectResponseFetcher {
    @Override
    public Call<ResponseBody> getCallableResponse() {
        return RetrofitCommunication.getRes().getTapTap(1);
    }
}
