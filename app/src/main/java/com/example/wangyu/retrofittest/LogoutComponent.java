package com.example.wangyu.retrofittest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutComponent {
    public static void logout() {
        String token = LoginComponent.getLogouttoken();
        Call<ResponseBody> logoutCall = RetrofitCommunication.getRes().logout(token);
        logoutCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() ==419) {
                    Call<ResponseBody> logoutRedirectCall2 = RetrofitCommunication.getRes().loginAfterlogout();
                    logoutRedirectCall2.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            PersistentCookieStore.setLoginStatus(false);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                } else {
                    Call<ResponseBody> logoutRedirectCall = RetrofitCommunication.getRes().getAppsRedirect();
                    logoutRedirectCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Call<ResponseBody> logoutRedirectCall2 = RetrofitCommunication.getRes().loginAfterlogout();
                            logoutRedirectCall2.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    PersistentCookieStore.setLoginStatus(false);
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("Logout", "退出登录异常" + t);

            }
        });
    }


}
