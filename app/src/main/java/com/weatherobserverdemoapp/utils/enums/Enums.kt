package com.weatherobserverdemoapp.utils.enums

import com.weatherobserverdemoapp.R

enum class WeatherIcon(override val value: Int) : IRefValueEnum {
    NONE(0) {
        override val imgRef = null
    },

    SUNNY(1) {
        override val imgRef = R.drawable.sunny
    },

    M_SUNNY(2) {
        override val imgRef = R.drawable.sunny
    },

    P_SUNNY(3) {
        override val imgRef = R.drawable.sunny
    },

    I_CLOUDS(4) {
        override val imgRef = R.drawable.sunny
    },

    H_SUNSHINE(5) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY(6) {
        override val imgRef = R.drawable.sunny
    },

    CLOUDY(7) {
        override val imgRef = R.drawable.sunny
    },

    DREARY(8) {
        override val imgRef = R.drawable.sunny
    },

    FOG(11) {
        override val imgRef = R.drawable.sunny
    },

    SHOWERS(12) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_SHOWERS(13) {
        override val imgRef = R.drawable.sunny
    },

    P_SUNNY_SHOWERS(14) {
        override val imgRef = R.drawable.sunny
    },

    T_STORMS(15) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_STORMS(16) {
        override val imgRef = R.drawable.sunny
    },

    P_SUNNY_STORM(17) {
        override val imgRef = R.drawable.sunny
    },

    RAIN(18) {
        override val imgRef = R.drawable.sunny
    },

    FLURRIES(19) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_FLURRIES(20) {
        override val imgRef = R.drawable.sunny
    },

    P_SUNNY_FLURRIES(21) {
        override val imgRef = R.drawable.sunny
    },

    SNOW(22) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_SNOW(23) {
        override val imgRef = R.drawable.sunny
    },

    ICE(24) {
        override val imgRef = R.drawable.sunny
    },

    SLEET(25) {
        override val imgRef = R.drawable.sunny
    },

    F_RAIN(26) {
        override val imgRef = R.drawable.sunny
    },

    RAIN_SNOW(29) {
        override val imgRef = R.drawable.sunny
    },

    HOT(30) {
        override val imgRef = R.drawable.sunny
    },

    COLD(31) {
        override val imgRef = R.drawable.sunny
    },

    WINDY(32) {
        override val imgRef = R.drawable.sunny
    },

    CLEAR(33) {
        override val imgRef = R.drawable.sunny
    },

    M_CLEAR(34) {
        override val imgRef = R.drawable.sunny
    },

    P_CLOUDY(35) {
        override val imgRef = R.drawable.sunny
    },

    I_CLOUDY(36) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY2(38) {
        override val imgRef = R.drawable.sunny
    },

    P_CLOUDY_SHOWERS(39) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_SHOWERS2(40) {
        override val imgRef = R.drawable.sunny
    },

    P_CLOUDY_STORMS(41) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_STORMS2(42) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_FLURRIES2(43) {
        override val imgRef = R.drawable.sunny
    },

    M_CLOUDY_SNOW2(44) {
        override val imgRef = R.drawable.sunny
    }
}