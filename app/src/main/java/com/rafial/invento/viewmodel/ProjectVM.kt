package com.rafial.invento.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rafial.invento.helper.ResultP
import com.rafial.invento.repository.AuthRepos
import com.rafial.invento.repository.ProjectRepos
import com.rafial.invento.response.project.CreateProjectResults
import com.rafial.invento.response.project.DetailProjectResp
import com.rafial.invento.response.project.GetAllProjectResp
import javax.inject.Inject

class ProjectVM @Inject constructor(private val projectRepo: ProjectRepos): ViewModel() {
    fun postProject(token: String,name:String,tag:List<String>,desc:String) =
        projectRepo.postProject(token,name,tag,desc)

    fun getAllProject(token: String) = projectRepo.getAllProject(token)

    fun getDetailProject(token: String) = projectRepo.getDetailProject(token)
}