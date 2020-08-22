package com.weatherobserverdemoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity()
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String
) : Serializable