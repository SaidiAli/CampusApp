package com.devhub.campus.utils

interface Mapper<F, T> {
    fun map(from: F): T
}