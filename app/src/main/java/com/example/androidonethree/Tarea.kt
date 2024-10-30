package com.example.androidonethree

data class Tarea(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val prioridad: String,
    val coste: Double,
    val completada: Boolean
)