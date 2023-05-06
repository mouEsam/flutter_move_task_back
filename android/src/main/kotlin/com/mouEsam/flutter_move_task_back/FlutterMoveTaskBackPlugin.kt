package com.mouEsam.flutter_move_task_back

import android.app.Activity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar



class FlutterMoveTaskBackPlugin : FlutterPlugin, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var handler: MethodCallHandlerImp

    companion object {

        private const val CHANNEL_NAME = "flutter_move_task_back"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val plugin = FlutterMoveTaskBackPlugin()
            plugin.setupChannel(registrar.messenger(), registrar.activity())
        }
    }

    private fun setupChannel(messenger: BinaryMessenger, activity: Activity?) {
        channel = MethodChannel(messenger, CHANNEL_NAME)
        handler = MethodCallHandlerImp()
        if (activity != null) {
            handler.setActivity(activity)
        }
        channel.setMethodCallHandler(handler)
    }

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, CHANNEL_NAME)
        handler = MethodCallHandlerImp()
        channel.setMethodCallHandler(handler)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        teardownChannel()
    }

    private fun teardownChannel() {
        channel.setMethodCallHandler(null)
    }

    override fun onDetachedFromActivity() {
        handler.release()
    }

    override fun onReattachedToActivityForConfigChanges(p0: ActivityPluginBinding) {
    }

    override fun onAttachedToActivity(p0: ActivityPluginBinding) {
        handler.setActivity(p0.activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }
}
