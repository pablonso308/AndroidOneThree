package com.example.androidonethree

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MiAdaptadorDeTarea(context: Context, tareas: List<Tarea>) : ArrayAdapter<Tarea>(context, 0, tareas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false)
        val tarea = getItem(position)
        view.findViewById<TextView>(R.id.tvNombre).text = tarea?.nombre
        view.findViewById<TextView>(R.id.tvDescripcion).text = tarea?.descripcion
        return view
    }
}