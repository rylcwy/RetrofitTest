package com.example.wangyu.retrofittest;

public class Versions {
    private String versionName;
    private String versionCode;
    private String versionDetail;
    private String versionDate;
    private String versionPublisher;
    private String forceUpdate;
    private String apkUrl;



    public Versions(String versionName, String versionCode, String versionDetail, String versionDate, String versionPublisher,
                    String forceUpdate,String apkUrl){
        this.versionCode=versionCode;
        this.versionDate=versionDate;
        this.versionDetail=versionDetail;
        this.versionName=versionName;
        this.versionPublisher=versionPublisher;
        this.forceUpdate=forceUpdate;
        this.apkUrl=apkUrl;

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

    public String getversionPublisher() {
        return versionPublisher;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public String getApkUrl(){return apkUrl;}

}
