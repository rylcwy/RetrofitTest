package com.example.wangyu.retrofittest;

import java.io.Serializable;

public class VersionInfo implements Serializable {
    public String versionId;
    public String versionCode;
    public String versionName;
    public String versionSize;
    public String versionForce;
    public String versionPublisher;
    public String versonDate;
    public String versionDetail;


    public  VersionInfo(){


    }

    public String getVersionDetail() {
        return versionDetail;
    }


    public void setVersionDetail(String versionDetail) {
        this.versionDetail = versionDetail;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(String versionSize) {
        this.versionSize = versionSize;
    }

    public String getVersionForce() {
        return versionForce;
    }

    public void setVersionForce(String versionForce) {
        this.versionForce = versionForce;
    }

    public String getVersionPublisher() {
        return versionPublisher;
    }

    public void setVersionPublisher(String versionPublisher) {
        this.versionPublisher = versionPublisher;
    }

    public String getVersonDate() {
        return versonDate;
    }

    public void setVersonDate(String versonDate) {
        this.versonDate = versonDate;
    }


    public String getVersionId() {
        return versionId;
    }





}
