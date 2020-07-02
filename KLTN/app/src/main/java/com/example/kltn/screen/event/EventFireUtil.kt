package datn.datn_expansemanagement.core.app.domain.excecutor

import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import com.example.kltn.screen.event.OnActionNotify


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