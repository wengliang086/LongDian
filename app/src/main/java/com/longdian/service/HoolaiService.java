package com.longdian.service;

import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherDataAll;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HoolaiService {

    @GET("user/login")
    Observable<HoolaiResponse<String>> login(@Query("account") String account, @Query("pwd") String pwd);

    @GET("user/actualdata")
    Observable<HoolaiResponse<List<CollectExtendData>>> realtimeData();

    @GET("user/test1")
    Observable<HoolaiResponse<Map<String, Long>>> pieChart();

    @GET("user/day/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportDay(@Query("searchDateTime") String searchDateTime);

    @GET("user/month/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportMonth(@Query("searchDateTime") String searchDateTime, @Query("stationName") String stationName);

    @GET("user/year/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportYear(@Query("year") String year, @Query("stationName") String stationName);

    @GET("user/econ/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportEconomics(@Query("beginTime") String beginTime, @Query("endTime") String endTime, @Query("stationName") String stationName);

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
