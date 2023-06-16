package com.rafial.invento.api

import com.rafial.invento.response.auth.LoginModel
import com.rafial.invento.response.auth.LoginResponse
import com.rafial.invento.response.auth.RegisterModel
import com.rafial.invento.response.auth.RegisterResponse
import com.rafial.invento.response.project.CreateProjectResponse
import com.rafial.invento.response.project.DetailProjectResp
import com.rafial.invento.response.project.GetAllProjectResp
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
     fun login(
        @Field("email") email: String,
        @Field("password") password: String,
//        @Body loginUser: LoginModel
    ) : LoginResponse

    @FormUrlEncoded
    @POST("register")
     fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
//        @Body registerUser: RegisterModel
    ): RegisterResponse
//===================================================================================================
    @GET("projects")
    suspend fun getAllProject(
        @Header("Authorization") token: String,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): GetAllProjectResp

    @POST("projects")
    fun postProject(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("tag") tag: List<String>,
        @Field("desc") desc: String,
    ): CreateProjectResponse

    @GET("projects/{project_id}")
    suspend fun getDetailProject(
        @Header("Authorization") token: String,
    ): DetailProjectResp
    //===================================================================================================
//    ADMIN PROJECT

//    @PUT("projects/{project_id}")
//    suspend fun updateProject():
//    @DELETE("projects/{project_id}")
//    suspend fun deleteMyProject():
//    @DELETE("projects/{project_id}/members/{user_id}")
//    suspend fun deleteMemberProject():
//    @GET("projects/{project_id}/join")
//    suspend fun Project():
//    @POST("projects/{project_id}/join/{request_id}")
//    suspend fun Project():
//    @POST("projects/{project_id}")
//    suspend fun deleteMemberProject():
}