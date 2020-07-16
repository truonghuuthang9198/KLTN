package com.example.kltn.screen.event


class EventFireUtil {
    companion object {
        @JvmStatic
        fun <T> fireEvent(event: OnActionData<T>?, data: T) {
            event?.onAction(data)
        }

        @JvmStatic
        fun fireEvent(event: OnActionNotify?) {
            event?.onActionNotify()
        }
    }
}