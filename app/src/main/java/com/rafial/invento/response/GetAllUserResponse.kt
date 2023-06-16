package com.rafial.invento.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetUserDataResponse(

	@field:SerializedName("users")
	val users: List<UsersItem?>? = null
) : Parcelable

@Parcelize
data class UsersItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tag")
	val tag: List<String?>? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
