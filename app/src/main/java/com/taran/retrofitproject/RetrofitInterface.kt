package com.taran.retrofitproject

import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @GET("users")
    fun getUsers(): Call<UserResponse>

    @GET("users/{userId}")
    fun getSingleUser(@Path("userId") userId: Int): Call<UserResponseItem>

    @GET("comments")
    fun getComments(): Call<CommentsResponse>

    @GET("comments/{commentsId}")
    fun getSingleComments(@Path("commentsId") commentsId: Int): Call<CommentsResponseItem>

    @GET("posts")
    fun getPosts(): Call<PostsResponse>
    @POST("users")
    @FormUrlEncoded
    fun createUser(@Header("Authorization") token: String,
                   @FieldMap () map: Map<String, String>,
                   //@Field("name") name: String,
                   //@Field("gender") gender: String,
                   //@Field("status") status: String,
    ): Call<UserResponseItem>
}