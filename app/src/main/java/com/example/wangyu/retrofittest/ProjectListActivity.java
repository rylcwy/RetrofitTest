package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = "ProjectListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityUtils.addActivity(ProjectListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Button betaButton = (Button) findViewById(R.id.TapTap_Beta);
        Button releaseButton = (Button) findViewById(R.id.TapTap);
        Button padButton = (Button) findViewById(R.id.TapTap_Pad);
        Button internationalButton = (Button) findViewById(R.id.TapTap_international);
        Button logOut = (Button) findViewById(R.id.Logout);
        betaButton.setOnClickListener(this);
        releaseButton.setOnClickListener(this);
        padButton.setOnClickListener(this);
        internationalButton.setOnClickListener(this);
        logOut.setOnClickListener(this);

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

            case R.id.Logout:
                LogoutComponent.logout();
                finish();
                Intent intent=new Intent();
                intent.setAction("android.intent.action.login");
                startActivity(intent);
                Toast.makeText(ProjectListActivity.this,"退出登录",Toast.LENGTH_SHORT).show();
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
        ActivityUtils.removeActivity(ProjectListActivity.this);
        LogUtil.d(TAG, "ProjectActivity onDestroy");
    }
}


