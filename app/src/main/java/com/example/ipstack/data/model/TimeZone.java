package com.example.ipstack.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class TimeZone implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("gmt_offset")
    private int GMT;
}
