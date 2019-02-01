package com.example.wangyu.retrofittest;

import android.os.Parcel;
import android.os.Parcelable;

public class VersionInfo implements Parcelable {
    public String versionId;
    public String versionCode;
    public String versionName;
    public String versionSize;
    public String versionForce;
    public String versionPublisher;
    public String versonDate;


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected VersionInfo(Parcel in) {
        versionId = in.readString();
        versionCode = in.readString();
        versionName = in.readString();
        versionSize = in.readString();
        versionForce = in.readString();
        versionPublisher=in.readString();
        versonDate=in.readString();

    }

    public static final Creator<VersionInfo> CREATOR = new Creator<VersionInfo>() {
        @Override
        public VersionInfo createFromParcel(Parcel in) {
            return new VersionInfo(in);
        }

        @Override
        public VersionInfo[] newArray(int size) {
            return new VersionInfo[size];
        }
    };
}
