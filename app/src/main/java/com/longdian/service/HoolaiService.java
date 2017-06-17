package com.longdian.service;

import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherDataAll;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HoolaiService {

    @GET("user/test")
    Observable<HoolaiResponse<String>> test(@Query("account") String account);

    @POST("user/stationList")
    Observable<HoolaiResponse<StationList>> stationList();

    @POST("user/weatherList")
    Observable<HoolaiResponse<List<WeatherData>>> weatherList();

    @POST("user/weatherIndex")
    Observable<HoolaiResponse<WeatherDataAll>> weatherIndex();

    @POST("login/getRegisterCode.hl")
    Observable<HoolaiResponse<String>> getRegistVerifyCode(@Query("productId") int productId, @Query("mobile") String mobile);


    @POST("user/{uidP}/bind/email/{emailP}")
    Observable<HoolaiResponse<String>> bindPhoneWithPassword(
            @Path("uidP") long uidP,
            @Path("emailP") String emailP,
            @Query("uid") long uid,
            @Query("email") String email
    );

}