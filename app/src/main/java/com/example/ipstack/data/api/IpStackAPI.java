package com.example.ipstack.data.api;

import com.example.ipstack.data.model.IpDataResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IpStackAPI {
    @GET("{ip}?access_key=60768222b4cb93ed5becec7a4dab1731")
    Single<IpDataResponse> getIpInfo(@Path("ip") String ip);

    @GET("https://api.ipstack.com/check?access_key=60768222b4cb93ed5becec7a4dab1731")
    Single<IpDataResponse> checkOwnIp();
}
