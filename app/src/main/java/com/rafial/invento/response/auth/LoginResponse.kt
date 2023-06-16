package com.rafial.invento.response.auth

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("uid")
	val uid: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)

data class LoginModel(
	val email : String,
	val password : String
)
