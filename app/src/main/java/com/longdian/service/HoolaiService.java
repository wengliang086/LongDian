package com.longdian.service;

import com.longdian.bean.OprInfo;
import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherDataAll;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HoolaiService {

    @GET("user/login")
    Observable<HoolaiResponse<OprInfo>> login(@Query("account") String account, @Query("pwd") String pwd);

    @GET("user/changePwd")
    Observable<HoolaiResponse<String>> changePwd(@Query("account") String account, @Query("oldPwd") String oldPwd, @Query("newPwd") String newPwd);

    @GET("user/actualdata")
    Observable<HoolaiResponse<List<CollectExtendData>>> realtimeData();

    @GET("user/historydata")
    Observable<HoolaiResponse<List<CollectExtendData>>> historydata(@Query("eTime") String eTime);

    @GET("user/test1")
    Observable<HoolaiResponse<Map<String, Float>>> pieChart();

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
    Observable<HoolaiResponse<List<WeatherData>>> weatherList(@Query("beginTime") String beginTime, @Query("endTime") String endTime);

    @POST("user/weatherDetail")
    Observable<HoolaiResponse<List<WeatherData>>> weatherDetail();

    @POST("user/weatherIndex")
    Observable<HoolaiResponse<WeatherDataAll>> weatherIndex();

    @POST("user/analysisWater")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisWater(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

    @POST("user/analysisElectricity")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisElectricity(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

    @POST("user/analysisHeat")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisHeat(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

}
