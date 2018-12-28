package com.example.wangyu.retrofittest;

public class Versions {
    private String versionName;
    private String versionCode;
    private String versionDetail;
    private String versionDate;
    private String versionReporter;
    private String forceUpdate;



    public Versions(String versionName, String versionCode, String versionDetail, String versionDate, String versionReporter,
                    String forceUpdate){
        this.versionCode=versionCode;
        this.versionDate=versionDate;
        this.versionDetail=versionDetail;
        this.versionName=versionName;
        this.versionReporter=versionReporter;
        this.forceUpdate=forceUpdate;

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

    public String getVersionReporter() {
        return versionReporter;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

}
