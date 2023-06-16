package com.rafial.invento.response.project

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CreateProjectResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results:  CreateProjectResults


)

data class CreateProjectResults(

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("tag")
	val tag: List<String>,

	@field:SerializedName("description_project")
	val desc: String,

)
