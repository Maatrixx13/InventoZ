package com.rafial.invento.response.auth

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RegisterResponse(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable

data class RegisterModel(
	val name : String,
	val email : String,
	val password : String
)
