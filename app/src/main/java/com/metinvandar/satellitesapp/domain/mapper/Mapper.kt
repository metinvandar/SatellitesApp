package com.metinvandar.satellitesapp.domain.mapper

interface Mapper<T, R> {
    fun mapFrom(data: T): R
}
