package com.example.kltn.event

interface LifeCycleMvpView {
    fun initMvpView()
    fun startMvpView()
    fun initData()
    fun stopMvpView()
    fun isHandleBackPressed(): Boolean
}