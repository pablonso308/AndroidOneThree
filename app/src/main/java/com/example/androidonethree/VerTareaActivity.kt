package com.example.androidonethree

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerTareaActivity : AppCompatActivity() {

    private lateinit var tvNombreTarea: TextView
    private lateinit var tvDescripcionTarea: TextView
    private lateinit var tvFechaTarea: TextView
    private lateinit var tvPrioridadTarea: TextView
    private lateinit var tvCosteTarea: TextView
    private lateinit var btnEditarTarea: Button
    private lateinit var btnEliminarTarea: Button

    private var tareaId: Long = -1 // ID de la tarea seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_tarea)

        // Inicializar los elementos de la interfaz
        tvNombreTarea = findViewById(R.id.tvNombreTarea)
        tvDescripcionTarea = findViewById(R.id.tvDescripcionTarea)
        tvFechaTarea = findViewById(R.id.tvFechaTarea)
        tvPrioridadTarea = findViewById(R.id.tvPrioridadTarea)
        tvCosteTarea = findViewById(R.id.tvCosteTarea)
        btnEditarTarea = findViewById(R.id.btnEditarTarea)
        btnEliminarTarea = findViewById(R.id.btnEliminarTarea)

        // Obtener el ID de la tarea desde el Intent
        tareaId = intent.getLongExtra("tareaId", -1)

        if (tareaId != -1L) {
            // Cargar datos de la tarea desde la base de datos (simulada aquí)
            cargarDatosTarea(tareaId)
        } else {
            Toast.makeText(this, "Error al cargar la tarea", Toast.LENGTH_SHORT).show()
            finish() // Regresar si no hay un ID válido
        }

        // Configurar el botón de editar tarea
        btnEditarTarea.setOnClickListener {
            val intent = Intent(this, RegistroTareaActivity::class.java)
            intent.putExtra("tareaId", tareaId) // Enviar ID de la tarea para editarla
            startActivity(intent)
        }

        // Configurar el botón de eliminar tarea
        btnEliminarTarea.setOnClickListener {
            confirmarEliminarTarea()
        }
    }

  private fun cargarDatosTarea(id: Long) {
    val tarea = obtenerTareaPorId(id) // Suponiendo que esta función obtiene una tarea desde la BD

    if (tarea != null) {
        // Asigna los datos de la tarea a los TextViews
        tvNombreTarea.text = tarea.nombre
        tvDescripcionTarea.text = tarea.descripcion
        tvFechaTarea.text = tarea.fecha
        tvPrioridadTarea.text = tarea.prioridad
        tvCosteTarea.text = tarea.coste.toString()
    } else {
        Toast.makeText(this, "Error al cargar la tarea", Toast.LENGTH_SHORT).show()
    }
}

    private fun confirmarEliminarTarea() {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Tarea")
            .setMessage("¿Estás seguro de que deseas eliminar esta tarea?")
            .setPositiveButton("Sí") { _, _ ->
                eliminarTarea(tareaId)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun eliminarTarea(id: Long) {
        // Aquí eliminarías la tarea de la base de datos
        // DatabaseHelper.eliminarTarea(id)
        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show()
        finish() // Regresar a la lista de tareas después de eliminar
    }

  private fun obtenerTareaPorId(id: Long): Tarea? {
    val dbHelper = DatabaseHelper(this)
    val db = dbHelper.readableDatabase

    val projection = arrayOf(
        "id",
        "nombre",
        "descripcion",
        "fecha",
        "prioridad",
        "coste",
        "completada"
    )

    val selection = "id = ?"
    val selectionArgs = arrayOf(id.toString())

    val cursor = db.query(
        "Tareas",   // The table to query
        projection, // The array of columns to return (pass null to get all)
        selection,  // The columns for the WHERE clause
        selectionArgs, // The values for the WHERE clause
        null,       // Don't group the rows
        null,       // Don't filter by row groups
        null        // The sort order
    )

    var tarea: Tarea? = null
    with(cursor) {
        if (moveToFirst()) {
            val tareaId = getLong(getColumnIndexOrThrow("id"))
            val nombre = getString(getColumnIndexOrThrow("nombre"))
            val descripcion = getString(getColumnIndexOrThrow("descripcion"))
            val fecha = getString(getColumnIndexOrThrow("fecha"))
            val prioridad = getString(getColumnIndexOrThrow("prioridad"))
            val coste = getDouble(getColumnIndexOrThrow("coste"))
            val completada = getInt(getColumnIndexOrThrow("completada")) > 0

            tarea = Tarea(
                id = tareaId,
                nombre = nombre,
                descripcion = descripcion,
                fecha = fecha,
                prioridad = prioridad,
                coste = coste,
                completada = completada
            )
        }
    }
    cursor.close()
    db.close()

    return tarea
}
}

