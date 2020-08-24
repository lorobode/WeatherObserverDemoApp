package com.weatherobserverdemoapp.utils.enums

import com.weatherobserverdemoapp.R

enum class WeatherIcon(override val value: Int) : IRefValueEnum {
    NONE(0) {
        override val imgRef = null
    },

    SUNNY(1) {
        override val imgRef = R.drawable.ic_sunny
    },

    M_SUNNY(2) {
        override val imgRef = R.drawable.ic_m_sunny
    },

    P_SUNNY(3) {
        override val imgRef = R.drawable.ic_p_sunny
    },

    I_CLOUDS(4) {
        override val imgRef = R.drawable.ic_i_clouds
    },

    H_SUNSHINE(5) {
        override val imgRef = R.drawable.ic_h_sunshine
    },

    M_CLOUDY(6) {
        override val imgRef = R.drawable.ic_m_cloudy
    },

    CLOUDY(7) {
        override val imgRef = R.drawable.ic_cloudy
    },

    DREARY(8) {
        override val imgRef = R.drawable.ic_dreary
    },

    FOG(11) {
        override val imgRef = R.drawable.ic_fog
    },

    SHOWERS(12) {
        override val imgRef = R.drawable.ic_showers
    },

    M_CLOUDY_SHOWERS(13) {
        override val imgRef = R.drawable.ic_m_cloudy_showers
    },

    P_SUNNY_SHOWERS(14) {
        override val imgRef = R.drawable.ic_p_sunny_showers
    },

    T_STORMS(15) {
        override val imgRef = R.drawable.ic_t_storms
    },

    M_CLOUDY_STORMS(16) {
        override val imgRef = R.drawable.ic_m_cloudy_storms
    },

    P_SUNNY_STORM(17) {
        override val imgRef = R.drawable.ic_p_sunny_storm
    },

    RAIN(18) {
        override val imgRef = R.drawable.ic_rain
    },

    FLURRIES(19) {
        override val imgRef = R.drawable.ic_flurries
    },

    M_CLOUDY_FLURRIES(20) {
        override val imgRef = R.drawable.ic_m_cloudy_flurries
    },

    P_SUNNY_FLURRIES(21) {
        override val imgRef = R.drawable.ic_p_sunny_flurries
    },

    SNOW(22) {
        override val imgRef = R.drawable.ic_snow
    },

    M_CLOUDY_SNOW(23) {
        override val imgRef = R.drawable.ic_m_cloudy_snow
    },

    ICE(24) {
        override val imgRef = R.drawable.ic_ice
    },

    SLEET(25) {
        override val imgRef = R.drawable.ic_sleet
    },

    F_RAIN(26) {
        override val imgRef = R.drawable.ic_f_rain
    },

    RAIN_SNOW(29) {
        override val imgRef = R.drawable.ic_rain_snow
    },

    HOT(30) {
        override val imgRef = R.drawable.ic_hot
    },

    COLD(31) {
        override val imgRef = R.drawable.ic_cold
    },

    WINDY(32) {
        override val imgRef = R.drawable.ic_windy
    },

    CLEAR(33) {
        override val imgRef = R.drawable.ic_clear
    },

    M_CLEAR(34) {
        override val imgRef = R.drawable.ic_m_clear
    },

    P_CLOUDY(35) {
        override val imgRef = R.drawable.ic_p_cloudy
    },

    I_CLOUDY(36) {
        override val imgRef = R.drawable.ic_i_cloudy
    },

    H_MOONLIGHT(37) {
        override val imgRef = R.drawable.ic_h_moonlight
    },

    M_CLOUDY2(38) {
        override val imgRef = R.drawable.ic_m_cloudy_2
    },

    P_CLOUDY_SHOWERS(39) {
        override val imgRef = R.drawable.ic_p_cloudy_showers
    },

    M_CLOUDY_SHOWERS2(40) {
        override val imgRef = R.drawable.ic_m_cloudy_showers_2
    },

    P_CLOUDY_STORMS(41) {
        override val imgRef = R.drawable.ic_p_cloudy_storms
    },

    M_CLOUDY_STORMS2(42) {
        override val imgRef = R.drawable.ic_m_cloudy_storms_2
    },

    M_CLOUDY_FLURRIES2(43) {
        override val imgRef = R.drawable.ic_m_cloudy_flurries_2
    },

    M_CLOUDY_SNOW2(44) {
        override val imgRef = R.drawable.ic_m_cloudy_snow_2
    }
}