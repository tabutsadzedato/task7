package com.example.facebook.app

import com.example.facebook.app.dto.*
import retrofit2.Call
import retrofit2.http.*

interface ReqResAPI {
    @GET("users")
    fun getUsers(@Query("page") page:Int):Call<ReqResData<List<User>>>
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long):Call<ReqResData<User>>

    @POST("users")
    fun addUser(@Body requestDto: UserRequestDto.UserRequest): Call<UserResponseDto.UserResponse>

    @PUT("users/{id}")
    fun updateUser(@Path("id") userId: Int, @Body requestDto: UserRequestDto): Call<UserResponseDto>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") userId: Int): Call<Unit>

    @POST("register")
    fun register(@Body requestDto: RegisterRequestDto.RegisterRequest): Call<RegisterResponseDto>
}