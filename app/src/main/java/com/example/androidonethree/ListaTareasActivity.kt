package com.example.androidonethree

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaTareasActivity : AppCompatActivity() {

    private lateinit var listaTareas: ListView
    private lateinit var btnHechas: Button
    private lateinit var btnPendientes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tareas)

        listaTareas = findViewById(R.id.listaTareas)
        btnHechas = findViewById(R.id.btnHechas)
        btnPendientes = findViewById(R.id.btnPendientes)

        cargarTareas()

        btnHechas.setOnClickListener {
            filtrarTareas(true)
        }

        btnPendientes.setOnClickListener {
            filtrarTareas(false)
        }

        listaTareas.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetalleTareaActivity::class.java)
            intent.putExtra("tareaId", id) // Pasar el ID de la tarea
            startActivity(intent)
        }
    }

    private fun cargarTareas() {
        // Aquí cargarías las tareas desde la base de datos y actualizarías el adaptador de la lista
        val tareas = obtenerTareas()
        listaTareas.adapter = MiAdaptadorDeTarea(this, tareas)
    }

    private fun obtenerTareas(): List<Tarea> {
        // Simulación de obtención de tareas desde la base de datos
        return listOf(
            Tarea(1, "Tarea 1", "Descripción 1", "2023-10-01", "Alta", 100.0, true),
            Tarea(2, "Tarea 2", "Descripción 2", "2023-10-02", "Media", 200.0, false)
        )
    }

    private fun filtrarTareas(completadas: Boolean) {
        val tareas = obtenerTareas().filter { it.completada == completadas }
        listaTareas.adapter = MiAdaptadorDeTarea(this, tareas)
    }
}



