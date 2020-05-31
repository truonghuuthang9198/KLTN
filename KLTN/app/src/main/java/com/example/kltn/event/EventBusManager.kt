package com.example.kltn.event

interface EventBusManager  : EventBusPublisher {
    fun register(handler: EventBusHandler)
    fun unregister(handler: EventBusHandler)
}