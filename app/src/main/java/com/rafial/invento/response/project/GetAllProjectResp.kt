package com.rafial.invento.response.project

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllProjectResp(

	@field:SerializedName("projects")
	val projects: List<ProjectsItem>
) : Parcelable

@Parcelize
data class ProjectsItem(

	@field:SerializedName("createdByName")
	val createdByName: String? = null,

	@field:SerializedName("project_id")
	val projectId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tag")
	val tag: List<String?>? = null,

	@field:SerializedName("createdById")
	val createdById: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
