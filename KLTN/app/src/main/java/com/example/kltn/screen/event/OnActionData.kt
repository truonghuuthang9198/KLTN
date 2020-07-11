package com.example.kltn.screen.event

import java.io.Serializable

@FunctionalInterface
interface OnActionData<in T> : Serializable {
    fun onAction(data: T)
}
