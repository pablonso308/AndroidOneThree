package com.example.androidonethree

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleTareaActivity : AppCompatActivity() {

    private lateinit var tvNombre: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvFecha: TextView
    private lateinit var tvPrioridad: TextView
    private lateinit var tvCoste: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_tarea)

        tvNombre = findViewById(R.id.tvNombre)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvFecha = findViewById(R.id.tvFecha)
        tvPrioridad = findViewById(R.id.tvPrioridad)
        tvCoste = findViewById(R.id.tvCoste)

        val tareaId = intent.getLongExtra("tareaId", -1)
        cargarDatosTarea(tareaId)
    }


    private fun cargarDatosTarea(tareaId: Long) {
        // Simulación de carga de datos de la tarea desde la base de datos usando el ID
        val tarea = obtenerTareaPorId(tareaId)
        tvNombre.text = tarea.nombre
        tvDescripcion.text = tarea.descripcion
        tvFecha.text = tarea.fecha
        tvPrioridad.text = tarea.prioridad
        tvCoste.text = tarea.coste.toString()
    }

    private fun obtenerTareaPorId(tareaId: Long): Tarea {
        // Simulación de obtención de una tarea por ID desde la base de datos
        return Tarea(tareaId, "Tarea $tareaId", "Descripción $tareaId", "2023-10-01", "Alta", 100.0, true)
    }
}