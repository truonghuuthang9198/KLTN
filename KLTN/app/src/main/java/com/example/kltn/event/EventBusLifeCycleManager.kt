package com.example.kltn.event

class EventBusLifeCycleManager {
    companion object {
        private var eventManager: SimpleEventBusManager? = null

        fun getInstance(): SimpleEventBusManager? {
            if (null == eventManager) {
                eventManager = SimpleEventBusManager()
            }
            return eventManager
        }
    }
}