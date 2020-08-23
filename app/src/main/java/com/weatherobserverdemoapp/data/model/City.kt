package com.weatherobserverdemoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity()
data class City(
    @PrimaryKey
    @Json(name = "Key")
    var id: Long,
    @Json(name = "LocalizedName")
    var name: String?,
    @Json(name = "Country")
    var country: Region?,
    @Json(name = "AdministrativeArea")
    var state: Region?
) : Serializable

data class Region(
    @Json(name = "ID")
    val code: String,
    @Json(name = "LocalizedName")
    val name: String
)