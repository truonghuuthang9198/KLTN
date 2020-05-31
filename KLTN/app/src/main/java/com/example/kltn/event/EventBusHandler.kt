package com.example.kltn.event
interface EventBusHandler {
    fun onReceiveEvent(data: EventBusData)
}