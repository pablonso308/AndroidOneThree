package com.example.androidonethree

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnListaTareas: Button
    private lateinit var btnRegistrarTarea: Button
    private lateinit var btnVerTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnListaTareas = findViewById(R.id.btnListaTareas)
        btnRegistrarTarea = findViewById(R.id.btnRegistrarTarea)
        btnVerTarea = findViewById(R.id.btnVerTarea)

        btnListaTareas.setOnClickListener {
            val intent = Intent(this, ListaTareasActivity::class.java)
            startActivity(intent)
        }

        btnRegistrarTarea.setOnClickListener {
            val intent = Intent(this, RegistroTareaActivity::class.java)
            startActivity(intent)
        }

        btnVerTarea.setOnClickListener {
            val intent = Intent(this, VerTareaActivity::class.java)
            intent.putExtra("tareaId", 1L) // Replace with the actual task ID you want to view
            startActivity(intent)
        }
    }
}