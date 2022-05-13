package com.codeludo.my_services_example.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

// 1 creamos el servicio
// 2 creamos una clase MyLocalBinder
class MyLocalBoundService : Service() {

    // 4 inicializamos una instancia de MyLocalBinder
    private val localBinder : MyLocalBinder = MyLocalBinder()

    // 6 realizamos alguna logica de negocio
    fun getSystemTime(): String{
        val simpleDateFormat : SimpleDateFormat = SimpleDateFormat(
            "ss:mm:hh  dd/mm/yyyy", Locale.US)
        return simpleDateFormat.format(Date())
    }

    // 2 creamos la clase MyLocalBinder
    inner class MyLocalBinder : Binder(){
        // 3 creamos una funcion que pide el servicio
        fun getService(): MyLocalBoundService = this@MyLocalBoundService
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "on binding Local bound service...", Toast.LENGTH_SHORT).show()
        // 5 retornamos el local Binder
        return localBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "unbinding Local bound service...", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Destroying Local bound service...", Toast.LENGTH_SHORT).show()
    }
}