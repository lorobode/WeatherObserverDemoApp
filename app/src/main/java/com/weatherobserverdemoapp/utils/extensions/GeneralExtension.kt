package com.weatherobserverdemoapp.utils.extensions

import com.squareup.moshi.Moshi
import com.weatherobserverdemoapp.utils.enums.EnumValueJsonAdapter
import com.weatherobserverdemoapp.utils.enums.IRefValueEnum
import kotlin.reflect.KClass

fun <T : IRefValueEnum> Moshi.Builder.addValueEnum(
    kClass: KClass<T>,
    unknownFallback: T? = null
): Moshi.Builder =
    this.add(kClass.java, EnumValueJsonAdapter.create(kClass.java, unknownFallback).nullSafe())