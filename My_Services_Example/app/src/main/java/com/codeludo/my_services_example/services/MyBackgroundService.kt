package com.codeludo.my_services_example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

// unbounded service
// Background service that is killed when activity
class MyBackgroundService : Service() {

    init {
       instance = this
    }

    companion object{
        private lateinit var instance: MyBackgroundService
        var isRunning = false

        fun stopService(){
            Log.i("MyBackgroundService", "Background Service stopping...")
            isRunning = false
            instance.stopSelf()
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Background Service starting", Toast.LENGTH_SHORT).show()

        Thread{
            isRunning = true
            while (isRunning){
                Log.i("MyBackgroundService", "Background Service is running...")
                try {
                    Thread.sleep(2000)
                }catch (e : InterruptedException){
                    Thread.currentThread().interrupt()
                }
            }
        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Background on Create Service...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Background on Destroy Service...", Toast.LENGTH_SHORT).show()
    }
}