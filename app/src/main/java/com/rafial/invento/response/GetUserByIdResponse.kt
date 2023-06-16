package com.rafial.invento.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetUserByIdResponse(

	@field:SerializedName("user")
	val user: User? = null
) : Parcelable

@Parcelize
data class UserId(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tag")
	val tag: List<String?>? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
