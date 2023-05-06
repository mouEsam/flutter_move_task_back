package com.mouEsam.flutter_move_task_back

import android.app.Activity
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * author: lixin(<a href="mailto:lixin@goldenridge.ltd">lixin@goldenridge.ltd</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2020-01-05 15:19<br/>
 *
 * maintainer: moustafa(<a href="mailto:essam.moustafa15@gmail.com">essam.moustafa15@gmail.com</a>)<br/>
 */
class MethodCallHandlerImp : MethodChannel.MethodCallHandler {
    companion object {
        private const val TAG = "mouEsam";
    }

    private var activity: java.lang.ref.WeakReference<Activity>? = null

    fun setActivity(activity: Activity) {
        this.activity = java.lang.ref.WeakReference(activity)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        val activity_ = activity?.get()
        if (activity_ == null) {
            Log.i(TAG, "android: moveTaskToBack: activity = null")
            result.error("activity can't be null", call.method, Exception("activity can't be null"))
        } else {
            try {
                if (call.method == "moveTaskToBack") {
                    val nonRoot: Boolean? = call.argument<Boolean>("nonRoot")
                    // nonRoot: If false then this only works if the activity is the root of a task; if true it will work for any activity in a task.
                    activity_.moveTaskToBack(nonRoot ?: true)
                    Log.i(TAG, "android: moveTaskToBack: $nonRoot")
                    result.success("")
                } else {
                    Log.i(TAG, "android: moveTaskToBack: notImplemented")
                    result.notImplemented()
                }
            } catch (e: Exception) {
                Log.i(TAG, "android: moveTaskToBack: exception")
                result.error("operation failed", call.method, e)
            }
        }
    }

    fun release() {
        activity != null
    }

}