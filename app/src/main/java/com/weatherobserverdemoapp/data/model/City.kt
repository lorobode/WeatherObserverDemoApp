package com.weatherobserverdemoapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.weatherobserverdemoapp.utils.converter.DateTypeConverter
import java.io.Serializable
import java.util.*

data class City(
    @Json(name = "Key")
    var id: Long,
    @Json(name = "LocalizedName")
    var name: String?,
    @Json(name = "Country")
    @Embedded(prefix = "country_")
    var country: Region?,
    @Json(name = "AdministrativeArea")
    @Embedded(prefix = "state_")
    var state: Region?
) : Serializable

data class Region(
    @Json(name = "ID")
    val code: String,
    @Json(name = "LocalizedName")
    val name: String
)

@Entity()
data class SelectedCity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @Embedded(prefix = "city_")
    var city: City,
    var userId: Int,
    @TypeConverters(DateTypeConverter::class)
    var windowStart: Date,
    @TypeConverters(DateTypeConverter::class)
    var windowEnd: Date
)