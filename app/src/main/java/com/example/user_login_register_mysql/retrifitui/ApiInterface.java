package com.example.user_login_register_mysql.retrifitui;

import com.example.user_login_register_mysql.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse> signinInformation(@Field("name") String name, @Field("email") String email, @Field("mobile") String mobile, @Field("address") String address, @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> logininInformation(@Field("email") String email, @Field("password") String password);
}
