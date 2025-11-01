package com.example.temas2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

class DBHelper(context: Context) : SQLiteOpenHelper(context, "notes_db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE notes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                content TEXT,
                created_at TEXT
            );
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun insertNote(title: String, content: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
            put("created_at", now())
        }
        val id = db.insert("notes", null, values)
        db.close()
        return id
    }

    fun updateNote(id: Int, title: String, content: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        val rows = db.update("notes", values, "id = ?", arrayOf(id.toString()))
        db.close()
        return rows
    }

    fun deleteNote(id: Int): Int {
        val db = writableDatabase
        val rows = db.delete("notes", "id = ?", arrayOf(id.toString()))
        db.close()
        return rows
    }

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM notes ORDER BY id DESC", null)
        if (cursor.moveToFirst()) {
            do {
                notes.add(
                    Note(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        content = cursor.getString(cursor.getColumnIndexOrThrow("content")),
                        createdAt = cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return notes
    }

    fun getNoteById(id: Int): Note? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notes WHERE id = ?", arrayOf(id.toString()))
        var note: Note? = null
        if (cursor.moveToFirst()) {
            note = Note(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                content = cursor.getString(cursor.getColumnIndexOrThrow("content")),
                createdAt = cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
            )
        }
        cursor.close()
        db.close()
        return note
    }

    private fun now(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}