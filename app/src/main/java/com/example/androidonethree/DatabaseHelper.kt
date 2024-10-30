package com.example.androidonethree

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Tareas (
                id INTEGER PRIMARY KEY,
                nombre TEXT,
                descripcion TEXT,
                fecha TEXT,
                prioridad TEXT,
                coste REAL,
                completada INTEGER
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Tareas")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "tareas.db"
        const val DATABASE_VERSION = 1
    }
}