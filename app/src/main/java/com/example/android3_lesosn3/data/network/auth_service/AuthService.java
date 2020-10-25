package com.example.android3_lesosn3.data.network.auth_service;

import com.example.android3_lesosn3.data.models.AllPostsModel;
import com.example.android3_lesosn3.data.models.Authorization;
import com.example.android3_lesosn3.data.network.HerokuappService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class AuthService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    AuthApi service = retrofit.create(AuthApi.class);

    public void authGet(String header, AuthCallBack authCallBack){
        Call<Authorization> call = service.auth(header);
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                if (response.isSuccessful() && response.body() != null){
                    authCallBack.onSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                authCallBack.onFailure(t);
            }
        });
    }

    public interface AuthCallBack{
        void onSuccess(Authorization authorization);
        void onFailure(Throwable throwable);
    }

    public interface AuthApi {
        @GET("user")
        Call<Authorization> auth(@Header("Authorization") String header);
    }
}
