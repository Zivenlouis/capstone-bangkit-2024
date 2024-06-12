package com.capstoneproject.auxilium.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class PhonesResponse(

	@field:SerializedName("PhonesResponse")
	val phonesResponse: List<PhonesResponseItem?>? = emptyList()
)

@Parcelize
data class PhonesResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("memory")
	val memory: String? = null,

	@field:SerializedName("os")
	val os: String? = null,

	@field:SerializedName("charging")
	val charging: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null,

	@field:SerializedName("nfc")
	val nfc: String? = null,

	@field:SerializedName("chipset")
	val chipset: String? = null,

	@field:SerializedName("battery")
	val battery: String? = null,

	@field:SerializedName("resolution")
	val resolution: String? = null,

	@field:SerializedName("main_camera_1")
	val mainCamera1: String? = null,

	@field:SerializedName("main_camera_2")
	val mainCamera2: String? = null,

	@field:SerializedName("colors")
	val colors: String? = null,

	@field:SerializedName("main_camera_3")
	val mainCamera3: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: @RawValue Any? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("earphone_jack")
	val earphoneJack: String? = null,

	@field:SerializedName("brand")
	val brand: String? = null,

	@field:SerializedName("selfie_camera")
	val selfieCamera: String? = null,

	@field:SerializedName("ram")
	val ram: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: @RawValue Any? = null
):Parcelable
