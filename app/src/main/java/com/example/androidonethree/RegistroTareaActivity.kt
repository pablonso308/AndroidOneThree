package com.example.androidonethree

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class RegistroTareaActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etFecha: EditText
    private lateinit var etPrioridad: EditText
    private lateinit var etCoste: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_tarea)

        etNombre = findViewById(R.id.etNombre)
        etDescripcion = findViewById(R.id.etDescripcion)
        etFecha = findViewById(R.id.etFecha)
        etPrioridad = findViewById(R.id.etPrioridad)
        etCoste = findViewById(R.id.etCoste)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnRegistrar.setOnClickListener {
            registrarTarea()
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun registrarTarea() {
        val nombre = etNombre.text.toString()
        val descripcion = etDescripcion.text.toString()
        val fecha = etFecha.text.toString()
        val prioridad = etPrioridad.text.toString()
        val coste = etCoste.text.toString()

        // Aquí guardarías la tarea en la base de datos.
        // DatabaseHelper.insertTarea(Tarea(nombre, descripcion, fecha, prioridad, coste))

        Toast.makeText(this, "Tarea registrada", Toast.LENGTH_SHORT).show()
        finish() // Vuelve a la lista de tareas
    }
}

