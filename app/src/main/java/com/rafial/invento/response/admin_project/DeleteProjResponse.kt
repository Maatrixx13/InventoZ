package com.rafial.invento.response.admin_project

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DeleteProjResponse(

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
