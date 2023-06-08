package com.gaoyun.roar.android.notifications

import android.util.Log
import com.gaoyun.notifications.NotificationHandler
import com.gaoyun.roar.model.domain.NotificationItem
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService(private val handler: NotificationHandler) : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        handler.handleImmediate(
            NotificationItem.Push(
                title = message.notification?.title ?: "",
                message = message.notification?.body ?: ""
            )
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "Fcm token: $token")
    }
}