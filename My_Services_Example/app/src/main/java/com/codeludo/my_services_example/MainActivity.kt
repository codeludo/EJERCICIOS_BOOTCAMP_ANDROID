package com.codeludo.my_services_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.codeludo.my_services_example.ui.BackgroundServiceActivity
import com.codeludo.my_services_example.ui.ForegroundServiceActivity
import com.codeludo.my_services_example.ui.LocalBindServiceActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShowBackgroundService : Button = findViewById(R.id.btnBackgroundService)
        val btnShowForegroundService : Button = findViewById(R.id.btnForegroundService)
        val btnShowLocalBindService : Button = findViewById(R.id.btnBindedService)

        btnShowBackgroundService.setOnClickListener {
            Intent(this, BackgroundServiceActivity::class.java).also {
                    intent -> startActivity(intent)
            }
        }

        btnShowForegroundService.setOnClickListener {
            Intent(this, ForegroundServiceActivity::class.java).also {
                    intent -> startActivity(intent)
            }
        }

        btnShowLocalBindService.setOnClickListener {
            Intent(this, LocalBindServiceActivity::class.java).also {
                    intent -> startActivity(intent)
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "MainActivity on Start...", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "MainActivity on Restart...", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "MainActivity on Resume...", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "MainActivity on Stop...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "MainActivity on Destroy...", Toast.LENGTH_SHORT).show()
    }
}