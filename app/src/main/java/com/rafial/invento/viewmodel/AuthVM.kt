package com.rafial.invento.viewmodel

import androidx.lifecycle.*
import com.rafial.invento.api.ApiService
import com.rafial.invento.repository.AuthRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor( private val authRepos: AuthRepos):ViewModel(){

        fun login(email: String, password:String) =
    authRepos.login(email, password)


    fun register(username: String, email: String, password: String) =
        authRepos.register(username, email, password)

    fun saveToken(key: String){
        viewModelScope.launch { authRepos.saveToken(key) }
    }

    fun deleteToken(){
        viewModelScope.launch { authRepos.deleteToken() }
    }
    fun setFirstTime(firstTime: Boolean) = authRepos.setFirstTime(firstTime)
    fun getToken() = authRepos.getToken()
    fun isFirstTime() = authRepos.isFirstTime()
    fun logout() = deleteToken()


//========================================================================================================================================================
//    fun register(name: String, email: String, password: String) : LiveData<ResultP<RegisterResponse>> = liveData {
//        emit(ResultP.Loading)
//        try{
//            val result = apiService.register(name, email, password)
//            emit(ResultP.Success(result))
//        }catch (e : Exception)
//        {
//            e.printStackTrace()
//            emit(ResultP.Error(e.message.toString()))
//        }
//
//    }
//    fun login(email: String, password: String) : LiveData<ResultP<LoginResponse>> = liveData {
//        emit(ResultP.Loading)
//        try {
//            val result = apiService.login(email, password)
//            emit(ResultP.Success(result))
//        }catch (e: Exception)
//        {
//            e.printStackTrace()
//            emit(ResultP.Error(e.message.toString()))
//        }
//    }
//
    
    companion object {
        private const val SignIn_error = "Sign in failed, use your valid email or password"
        private const val SignUp_error = "Sign up failed, make sure you have filled correctly"
        private val TAG = AuthVM::class.java.simpleName
        @Volatile
        private var INSTANCE: AuthVM? = null
        const val DEFAULT_VALUE = "Coder"

//        private val TOKEN = stringPreferencesKey("token")
//        private val STATE_KEY = booleanPreferencesKey("state")
//
//        fun getInstance(
//            dataStore: DataStore<Preferences>,
//            apiService: ApiService,
//            pref: UserPreference
//        ): AuthVM {
//            return INSTANCE ?: synchronized(this) {
//                val instance = AuthVM(dataStore, pref,apiService)
//                INSTANCE = instance
//                instance
//            }
//        }
    }

}


