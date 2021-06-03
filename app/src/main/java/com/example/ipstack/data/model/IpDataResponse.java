package com.example.ipstack.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class IpDataResponse implements Serializable {

    @SerializedName("ip")
    private String ipAddress;

    @SerializedName("city")
    private String city;

    @SerializedName("country_name")
    private String country;

    @SerializedName("region_name")
    private String region;

    @SerializedName("country_flag")
    private String countryFlag;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("connection")
    private Connection connection;

    @SerializedName("time_zone")
    private TimeZone timezone;

    @Override
    public String toString() {
        return "IpDataResponse{" +
                "ipAddress='" + ipAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", connection=" + connection +
                ", timezone=" + timezone +
                '}';
    }
}