package com.example.wangyu.retrofittest;

public class Versions {
    private String versionName;
    private String versionCode;
    private String versionDetail;
    private String versionDate;

    public Versions(String versionName,String versionCode,String versionDetail,String versionDate){
        this.versionCode=versionCode;
        this.versionDate=versionDate;
        this.versionDetail=versionDetail;
        this.versionName=versionName;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getVersionDetail() {
        return versionDetail;
    }

    public String getVersionDate() {
        return versionDate;
    }
}
