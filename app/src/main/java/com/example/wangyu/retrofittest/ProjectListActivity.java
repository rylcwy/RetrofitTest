package com.example.wangyu.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG="ProjectListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Button betaButton = (Button) findViewById(R.id.TapTap_Beta);
        Button releaseButton = (Button) findViewById(R.id.TapTap);
        Button padButton = (Button) findViewById(R.id.TapTap_Pad);
        Button internationalButton = (Button) findViewById(R.id.TapTap_international);
        betaButton.setOnClickListener(this);
        releaseButton.setOnClickListener(this);
        padButton.setOnClickListener(this);
        internationalButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TapTap_Beta:
                ListActivity.actionStart(MyApplication.getContext(), new BetaProjectResponseFetcher());
                break;

            case R.id.TapTap:
                ListActivity.actionStart(MyApplication.getContext(), new ReleaseProjectResponseFetcher());
                break;

            case R.id.TapTap_Pad:
                ListActivity.actionStart(MyApplication.getContext(), new HdProjectResponseFetcher());
                break;

            case R.id.TapTap_international:
                ListActivity.actionStart(MyApplication.getContext(), new InternationalProjectResponseFetcher());
                break;

            default:
                break;
        }

    }

    public static class BetaProjectResponseFetcher implements ProjectResponseFetcher {
        private static final long serialVersionUID = 1L;

        @Override
        public Call<ResponseBody> getCallableResponse(int pageNumber) {
            return RetrofitCommunication.getRes().getTapTapBeta(pageNumber);
        }
    }

    public static class ReleaseProjectResponseFetcher implements ProjectResponseFetcher {
        private static final long serialVersionUID = 1L;

        @Override
        public Call<ResponseBody> getCallableResponse(int pageNumber) {
            return RetrofitCommunication.getRes().getTapTapRelease(pageNumber);
        }
    }

    public static class HdProjectResponseFetcher implements ProjectResponseFetcher {
        private static final long serialVersionUID = 1L;

        @Override
        public Call<ResponseBody> getCallableResponse(int pageNumber) {
            return RetrofitCommunication.getRes().getTapTapHD(pageNumber);
        }
    }

    public static class InternationalProjectResponseFetcher implements ProjectResponseFetcher {
        private static final long serialVersionUID = 1L;

        @Override
        public Call<ResponseBody> getCallableResponse(int pageNumber) {
            return RetrofitCommunication.getRes().getTapTapInternational(pageNumber);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,"ProjectActivity onDestroy");
    }
}


