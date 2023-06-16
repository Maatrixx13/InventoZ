package com.rafial.invento.repository

import com.rafial.invento.api.ApiService
import com.rafial.invento.helper.ResultP
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rafial.invento.response.project.CreateProjectResults
import com.rafial.invento.response.project.DetailProjectResp
import com.rafial.invento.response.project.GetAllProjectResp

class ProjectRepos @Inject constructor(private val apiService: ApiService) {
//
////    EXPERIMENTAL CREATEPROJRESULT AS
    fun postProject(token: String,name:String,tag:List<String>,desc:String): LiveData<ResultP<CreateProjectResults>> = liveData {
        emit(ResultP.Loading)
        try {
            val response = apiService.postProject(token, name,tag,desc)
            val project = response.results
            emit(ResultP.Success(project))
        } catch (e: Exception) {
            emit(ResultP.Error(e.message.toString()))
        }
    }

    fun getAllProject(token: String): LiveData<ResultP<GetAllProjectResp>> = liveData {
        emit(ResultP.Loading)
        try {
            val response = apiService.getAllProject("Bearer $token" )
            emit(ResultP.Success(response))
        } catch (e: Exception) {
            emit(ResultP.Error(e.message.toString()))
        }
    }fun getDetailProject(token: String): LiveData<ResultP<DetailProjectResp>> = liveData {
        emit(ResultP.Loading)
        try {
            val response = apiService.getDetailProject(token = token)
            emit(ResultP.Success(response))
        } catch (e: Exception) {
            emit(ResultP.Error(e.message.toString()))
        }
    }


}