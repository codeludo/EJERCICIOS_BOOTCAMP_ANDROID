package com.codeludo.my_services_example.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeludo.my_services_example.R
import com.codeludo.my_services_example.services.MyBackgroundService
import com.codeludo.my_services_example.services.MyForegroundService

class ForegroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)


        if (!foregroundServiceRunning()) {
            Intent(this, MyForegroundService::class.java).also { intent ->
                startForegroundService(intent)
            }
        }

        val btnStopForegroundService: Button = findViewById(R.id.btnStopForegroundService)
        btnStopForegroundService.setOnClickListener {
            MyForegroundService.stopService()
            Toast.makeText(this,"Foreground Service stopped...", Toast.LENGTH_SHORT).show()

        }
    }

    private fun foregroundServiceRunning(): Boolean {
        val activityManager: ActivityManager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (MyForegroundService::class.java.name.equals(service.service.className)) {
                return true;
            }
        }
        return false
    }


    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Foreground Activity on Start...", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "Foreground Activity on Restart...", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Foreground Activity on Resume...", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Foreground Activity on Stop...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Foreground Activity on Destroy...", Toast.LENGTH_SHORT).show()
    }
}