package com.example.temas2

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var db: DBHelper
    lateinit var etTitle: EditText
    lateinit var etContent: EditText
    lateinit var btnSave: Button
    var noteId: Int = -1
    var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        db = DBHelper(this)
        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnSave = findViewById(R.id.btnSave)

        noteId = intent.getIntExtra("note_id", -1)
        isEdit = noteId != -1

        if (isEdit) {
            title = "Editar nota"
            loadNote()
        } else {
            title = "Nueva nota"
        }

        btnSave.setOnClickListener {
            val titleTxt = etTitle.text.toString().trim()
            val contentTxt = etContent.text.toString().trim()

            if (TextUtils.isEmpty(titleTxt)) {
                etTitle.error = "El título es requerido"
                return@setOnClickListener
            }

            if (isEdit) {
                val rows = db.updateNote(noteId, titleTxt, contentTxt)
                Toast.makeText(this, if (rows > 0) "Nota actualizada" else "Error", Toast.LENGTH_SHORT).show()
            } else {
                val id = db.insertNote(titleTxt, contentTxt)
                Toast.makeText(this, if (id > 0) "Nota guardada" else "Error", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    private fun loadNote() {
        val n = db.getNoteById(noteId)
        if (n != null) {
            etTitle.setText(n.title)
            etContent.setText(n.content)
        }
    }
}
