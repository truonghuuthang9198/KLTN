package com.example.kltn.event

interface LifeCycleDispatcherSetter<in T: LifeCycleMvpView> {
    fun addLifeCycle(lifeCycle: T)
}