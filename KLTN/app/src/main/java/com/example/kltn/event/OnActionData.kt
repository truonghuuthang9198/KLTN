package com.example.kltn.event

import java.io.Serializable

@FunctionalInterface
interface OnActionData<in T> : Serializable {
    fun onAction(data: T)
}
