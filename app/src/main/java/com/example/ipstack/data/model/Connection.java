package com.example.ipstack.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Connection implements Serializable {
   @SerializedName("isp")
   private String ISP;
}
