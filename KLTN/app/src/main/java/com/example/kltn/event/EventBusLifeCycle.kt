package com.example.kltn.event

class EventBusLifeCycle constructor(private val onEventBusActionData: OnActionData<EventBusData>) : LifeCycleAndroidMvpView,
    EventBusHandler {
    override fun onReceiveEvent(data: EventBusData) {
        onEventBusActionData.onAction(data)
    }

    override fun onViewResult(viewResult: ViewResult) {
    }

    override fun onRequestPermissionsResult(permissionsResult: PermissionsResult) {
    }

    override fun initMvpView() {
    }

    override fun startMvpView() {
        EventBusLifeCycleManager.getInstance()?.register(this)
    }

    override fun initData() {
    }

    override fun stopMvpView() {
        EventBusLifeCycleManager.getInstance()?.unregister(this)
    }

    override fun isHandleBackPressed(): Boolean = false

    fun sendData(data: EventBusData){
        EventBusLifeCycleManager.getInstance()?.publishEvent(data)
    }
}