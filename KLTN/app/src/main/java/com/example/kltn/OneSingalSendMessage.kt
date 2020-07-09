package com.example.kltn

import android.app.Application
import android.util.Log
import com.onesignal.OneSignal


class OneSingalSendMessage : Application() {
    override fun onCreate() {
        super.onCreate()

        // OneSignal Initialization
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
        val status = OneSignal.getPermissionSubscriptionState()
//        status.permissionStatus.enabled
//        status.subscriptionStatus.subscribed
//        status.subscriptionStatus.userSubscriptionSetting
//        status.subscriptionStatus.userId
        Log.d("Thang",status.subscriptionStatus.userId)
//        status.subscriptionStatus.pushToken
    }
}