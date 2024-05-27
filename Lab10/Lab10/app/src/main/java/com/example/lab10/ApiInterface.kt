package com.example.lab10

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Int) : Response<Post>

    @POST("/posts/")
    suspend fun addPost(@Body post: Post) : Response<AddPostResponse>
}