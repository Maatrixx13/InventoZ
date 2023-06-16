package com.rafial.invento.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rafial.invento.api.ApiService
import com.rafial.invento.helper.ResultP
import com.rafial.invento.response.UserPreference
import com.rafial.invento.response.auth.LoginResponse
import com.rafial.invento.response.auth.RegisterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class AuthRepos @Inject  constructor(private val apiService: ApiService,private val userPreferences: UserPreference):
    CoroutineScope {
    private val _login = MutableLiveData<LoginResponse>()
    val login : LiveData<LoginResponse> = _login
//    private val _register = MutableLiveData<Register>()
//    val register : LiveData<Register> = _register
private val loginResult = MutableLiveData<ResultP<LoginResponse>>()


//    fun login(email : String, password: String): LiveData<ResultP<LoginResponse>> {
//        NetworkModule.provideApiService().login(email, password)
//            .enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    if (response.isSuccessful) {
//                        val loginInfo = response.body()
//                        if (loginInfo != null) {
//                            loginResult.value = ResultP.Success(loginInfo)
//                        } else {
//                            loginResult.value = ResultP.Error(SignIn_error)
//                            Log.e(TAG, "Failed: Login Info is null")
//                        }
//                    } else {
//                        loginResult.value = ResultP.Error(SignIn_error)
//                        Log.e(TAG, "Failed: Response Unsuccessful - ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    loginResult.value = ResultP.Error(SignIn_error)
//                    Log.e(TAG, "Failed: Response Failure - ${t.message.toString()}")
//                }
//
//            })
//        return loginResult
//    }

    fun login(email: String, password: String): LiveData<ResultP<LoginResponse>> = liveData {
        emit(ResultP.Loading)
        try {
            val response = apiService.login(email, password)
            emit(ResultP.Success(response))
        } catch (e: Exception) {
            emit(ResultP.Error(e.message.toString()))
        }
    }

    fun register(
        username: String,
        email: String,
        password: String
    ): LiveData<ResultP<RegisterResponse>> = liveData {
        emit(ResultP.Loading)
        try {
            val response = apiService.register(username, email, password)
            emit(ResultP.Success(response))
        } catch (e: Exception) {
            emit(ResultP.Error(e.message.toString()))
        }
    }
    fun saveToken(token: String) {
        launch(Dispatchers.IO) {
            userPreferences.saveToken(token)
        }
    }
    fun deleteToken() {
        launch(Dispatchers.IO) {
            userPreferences.deleteToken()
        }
    }

    fun getToken() = userPreferences.getToken().asLiveData()

    fun isFirstTime() = userPreferences.isFirstTime().asLiveData()

    fun setFirstTime(firstTime: Boolean) {
        launch(Dispatchers.IO) {
            userPreferences.setFirstTime(firstTime)
        }
    }
    companion object {
        private const val SignIn_error = "Sign in failed, use your valid email or password"
        private const val SignUp_error = "Sign up failed, make sure you have filled correctly"
        private val TAG = AuthRepos::class.java.simpleName
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}