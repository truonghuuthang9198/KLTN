package com.example.kltn.event

interface EventBusPublisher {
    fun publishEvent(data: EventBusData)
}