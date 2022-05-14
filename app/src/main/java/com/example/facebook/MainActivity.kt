package com.example.facebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.facebook.app.RestClient
import com.example.facebook.app.dto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.initClient()
        RestClient.reqResApi.getUsers(1).enqueue(
            object: Callback<ReqResData<List<User>>>{
                override fun onResponse(
                    call: Call<ReqResData<List<User>>>,
                    response: Response<ReqResData<List<User>>>
                ) {
                    if(response.isSuccessful && response.body() != null){
                        response.body()!!.data?.forEach { user -> Log.d("Zuras Log",user.toString()) }
                    }
                }

                override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
        addUser()
        updateUser()
        deleteUser()
        register()

    }
    private fun addUser() {
        RestClient.reqResApi.addUser(UserRequestDto.UserRequest(name = "dato", "student")).enqueue(
            object: Callback<UserResponseDto.UserResponse> {
                override fun onResponse(
                    call: Call<UserResponseDto.UserResponse>,
                    responseDto: Response<UserResponseDto.UserResponse>
                ) {
                    if(responseDto.isSuccessful && responseDto.body() != null){
                        responseDto.body()?.let { data -> Log.d("AddUser ", data.toString()) }
                    }
                }

                override fun onFailure(call: Call<UserResponseDto.UserResponse>, t: Throwable) {
                    Log.d("AddUser", "Failed")
                }

            }
        )
    }

    private fun updateUser() {
        RestClient.reqResApi.updateUser(1, UserRequestDto(name = "dato", "student")).enqueue(
            object: Callback<UserResponseDto> {
                override fun onResponse(
                    call: Call<UserResponseDto>,
                    responseDto: Response<UserResponseDto>
                ) {
                    if(responseDto.isSuccessful && responseDto.body() != null){
                        responseDto.body()?.let { data -> Log.d("UpdateUser ", data.toString()) }
                    }
                }

                override fun onFailure(call: Call<UserResponseDto>, t: Throwable) {
                    Log.d("UpdateUser", "Failed")
                }

            }
        )
    }

    private fun deleteUser() {
        RestClient.reqResApi.deleteUser(1).enqueue(
            object: Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    if(response.isSuccessful){
                        Log.d("DeleteUser"," Success")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("DeleteUser", "Failed")
                }

            }
        )
    }

    private fun register() {
        RestClient.reqResApi.register(RegisterRequestDto.RegisterRequest("dato@mail.com", "123456")).enqueue(
            object: Callback<RegisterResponseDto> {
                override fun onResponse(
                    call: Call<RegisterResponseDto>,
                    responseDto: Response<RegisterResponseDto>
                ) {
                    if(responseDto.isSuccessful && responseDto.body() != null){
                        responseDto.body()?.let { data -> Log.d("Register ", data.toString()) }
                    }
                }

                override fun onFailure(call: Call<RegisterResponseDto>, t: Throwable) {
                    Log.d("Register", "Failed")
                }

            }
        )
    }
}