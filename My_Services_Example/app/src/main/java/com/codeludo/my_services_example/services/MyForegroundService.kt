package com.codeludo.my_services_example.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.codeludo.my_services_example.R

class MyForegroundService : Service(){

    init {
        instance = this
    }

    companion object{
        private lateinit var instance: MyForegroundService
        var isRunning = false

        fun stopService(){
            Log.i("MyForegroundService", "Foreground Service stopping...")
            isRunning = false
            instance.stopForeground(true)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()

        Thread{
            isRunning = true
            while (isRunning){
                Log.i("MyForegroundService", "Foreground Service is running...")
                try {
                    Thread.sleep(2000)
                }catch (e : InterruptedException){
                    Thread.currentThread().interrupt()
                }
            }
        }.start()
        val CHANNEL_ID = "Foreground Service ID"
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_ID,
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_launcher_background)
        startForeground(1001, notification.build())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Foreground on Create Service...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Foreground on Destroy Service...", Toast.LENGTH_SHORT).show()
    }
}