//package com.rafial.invento.di
//
//import android.app.Application
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.rafial.invento.api.ApiService
//import com.rafial.invento.response.UserPreference
//import com.rafial.invento.viewmodel.AuthVM
//import com.rafial.invento.viewmodel.ProjectVM
//
//class ViewModelFactory (private val pref: UserPreference,private val apiService: ApiService) :
//    ViewModelProvider.NewInstanceFactory(){
//
//    private lateinit var mApplication: Application
//
//    fun setApplication(application: Application){
//        mApplication = application
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AuthVM::class.java)){
//            return AuthVM(pref,apiService) as T
//        }
//        if (modelClass.isAssignableFrom(ProjectVM::class.java)) {
//            return ProjectVM() as T
//        }
//
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//
//}