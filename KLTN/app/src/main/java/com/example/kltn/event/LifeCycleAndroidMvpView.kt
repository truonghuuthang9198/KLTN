package com.example.kltn.event


interface LifeCycleAndroidMvpView : LifeCycleMvpView {
    fun onViewResult(viewResult: ViewResult)
    fun onRequestPermissionsResult(permissionsResult: PermissionsResult)
}