package com.longdian.service;

import com.longdian.fragment.base.model.StationList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface HoolaiService {

    @GET("user/test")
    Observable<HoolaiResponse<String>> test(@Query("account") String account);

    @POST("user/stationList")
    Observable<HoolaiResponse<StationList>> stationList();

    @POST("public/user/{channel}/{channelId}/login/{loginType}")
    Observable<HoolaiResponse<String>> login(@Path("channel") String channel, @Path("channelId") int channelId, @Path("loginType") int loginType, @Query("type") int type, @Query("passport") String account, @Query("password") String password);

    @POST("public/user/email/findpwdcode")
    Observable<HoolaiResponse<String>> getFindPwdVerifyCode(@Query("email") String email);

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
