package com.rafial.invento.response.admin_project

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class UpdateProjResponse(

	@field:SerializedName("project")
	val project: Project? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class Project(

	@field:SerializedName("createdByName")
	val createdByName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tag")
	val tag: List<String?>? = null,

	@field:SerializedName("createdById")
	val createdById: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
