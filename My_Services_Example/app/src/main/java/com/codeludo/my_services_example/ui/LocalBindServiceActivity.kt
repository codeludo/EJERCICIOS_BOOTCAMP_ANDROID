package com.codeludo.my_services_example.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codeludo.my_services_example.R
import com.codeludo.my_services_example.services.MyLocalBoundService

class LocalBindServiceActivity : AppCompatActivity() {
    // referenciamos el servicio y un flag de enlazado o no
    private lateinit var mService: MyLocalBoundService
    private var mBound: Boolean = false

    lateinit var txtSystemTime : TextView
    lateinit var btnShowSystemTimer: Button

    // definimos los llamados para el service binding para pasar a bindService
    private val connection = object : ServiceConnection{

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MyLocalBoundService.MyLocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName?) {
            mBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_bind)
        Toast.makeText(this, "onCreate LocalBindService Activity...", Toast.LENGTH_SHORT).show()
    }

    // El servicio inicia cuando se ingresa a la actividad y esta en onStart
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart LocalBindService Activity...", Toast.LENGTH_SHORT).show()
        Intent(this, MyLocalBoundService::class.java).also{intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "onResume LocalBindService Activity...", Toast.LENGTH_SHORT).show()
        txtSystemTime = findViewById(R.id.txtSystemTime)
        btnShowSystemTimer = findViewById(R.id.btnShowTime)

        btnShowSystemTimer.setOnClickListener {
            txtSystemTime.text = mService.getSystemTime()
        }
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause LocalBindService Activity...", Toast.LENGTH_SHORT).show()
    }

    // cuando la actividad entra en onStop el servicio se desenlaza
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop LocalBindService Activity...", Toast.LENGTH_SHORT).show()
        unbindService(connection)
        mBound = false
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy LocalBindService Activity...", Toast.LENGTH_SHORT).show()
    }

}