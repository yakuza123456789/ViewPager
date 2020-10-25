package com.example.android3_lesosn3.data.network;

import com.example.android3_lesosn3.data.models.AllPostsModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class HerokuappService {

    public interface HerokuAPI {
        @GET("posts")
        Call<ArrayList<AllPostsModel>> posts();

        @DELETE("posts/{postId}")
        Call<AllPostsModel> postById
                (@Path("postId") int postId);

        @POST("posts/")
            Call<AllPostsModel> createPost(@Body AllPostsModel allPostsModel);

        @POST("posts/") Call<AllPostsModel> updatePost (@Path("postId") int postId, AllPostsModel allPostsModel);

    }



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://android-3-mocker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    HerokuAPI service = retrofit.create(HerokuAPI.class);

    public void getPosts(ArrayCallBackPosts callBackPosts){
        Call<ArrayList<AllPostsModel>> call = service.posts();
        call.enqueue(new Callback<ArrayList<AllPostsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AllPostsModel>> call, Response<ArrayList<AllPostsModel>> response) {
                callBackPosts.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<AllPostsModel>> call, Throwable t) {
                callBackPosts.onFailure(t);

            }
        });
    }

    public void deletePost(int id, CallBackPost callBackDeletePost){
        Call<AllPostsModel> call = service.postById(id);
        call.enqueue(new Callback<AllPostsModel>() {
            @Override
            public void onResponse(Call<AllPostsModel> call, Response<AllPostsModel> response) {
            callBackDeletePost.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AllPostsModel> call, Throwable t) {
                callBackDeletePost.onFailure(t);
            }
        });
    }

    public void cratePost(AllPostsModel allPostsModel, CallBackPost callBackPost){
        Call<AllPostsModel> call = service.createPost(allPostsModel);
        call.enqueue(new Callback<AllPostsModel>() {
            @Override
            public void onResponse(Call<AllPostsModel> call, Response<AllPostsModel> response) {
                callBackPost.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AllPostsModel> call, Throwable t) {
                callBackPost.onFailure(t);
            }
        });
    }

    public void updatePost(int id, AllPostsModel allPostsModel, CallBackPost callBackPost){
        Call<AllPostsModel> call = service.updatePost(id, allPostsModel);
        call.enqueue(new Callback<AllPostsModel>() {
            @Override
            public void onResponse(Call<AllPostsModel> call, Response<AllPostsModel> response) {
                callBackPost.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AllPostsModel> call, Throwable t) {
                callBackPost.onFailure(t);
            }
        });
    }

    public interface CallBackPost{
        void onSuccess(AllPostsModel allPostsModel);
        void onFailure(Throwable throwable);
    }

    public interface ArrayCallBackPosts{
        void onSuccess(ArrayList<AllPostsModel> allPostsModels);
        void onFailure(Throwable throwable);
    }



}
