package com.example.anuin.introNlogin.model;

import com.google.gson.annotations.SerializedName;

public class Walkthrough {
    @SerializedName("intro_title")
    private String title;
    @SerializedName("intro_desc")
    private String desc;
    @SerializedName("intro_image")
    private String imgSrc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
