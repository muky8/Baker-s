package com.example.mukhter.bakers.data;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by MUKHTER on 10/07/2017.
 */

public class Step extends Recipe implements Parcelable{

    private String id, shortDesc, desc, videoUrl, thumbnailUrl;

    public Step(String id, String shortDesc, String desc, String videoUrl, String thumbnailUrl){
        this.id = id;
        this.shortDesc = shortDesc;
        this.desc = desc;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    protected Step(Parcel in) {
        id = in.readString();
        shortDesc = in.readString();
        desc = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(shortDesc);
        parcel.writeString(desc);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailUrl);
    }

    @Override
    public String toString() {
        return "Step{" +
                "id='" + id + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", desc='" + desc + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
