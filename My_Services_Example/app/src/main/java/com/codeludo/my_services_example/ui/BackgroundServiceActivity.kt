package com.codeludo.my_services_example.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeludo.my_services_example.R
import com.codeludo.my_services_example.services.MyBackgroundService

class BackgroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        val txtServiceInfoStatus : TextView = findViewById(R.id.txtServiceInfo)
        val btnStopService : Button = findViewById(R.id.btnStopService)


        // inicia el servicio en el onCreate
        Intent(this, MyBackgroundService::class.java).also {
                intent -> startService(intent)
        }




        btnStopService.setOnClickListener {
            MyBackgroundService.stopService()
            txtServiceInfoStatus.visibility = View.VISIBLE
            txtServiceInfoStatus.text = "Background Service stopped.."

        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Background Activity on Start..", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "Background Activity on Restart...", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Background Activity on Resume...", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Background Activity on Stop...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Background Activity on Destroy...", Toast.LENGTH_SHORT).show()
    }
}