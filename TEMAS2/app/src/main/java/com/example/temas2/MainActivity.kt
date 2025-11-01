package com.example.tema2



import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var db: DBHelper
    lateinit var adapter: NoteAdapter
    lateinit var rvNotes: RecyclerView
    lateinit var tvEmpty: TextView
    lateinit var fabAdd: FloatingActionButton
    var notesList = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        rvNotes = findViewById(R.id.rvNotes)
        tvEmpty = findViewById(R.id.tvEmpty)
        fabAdd = findViewById(R.id.fabAdd)

        adapter = NoteAdapter(notesList,
            onEdit = { note -> goToEdit(note) },
            onDelete = { note -> confirmDelete(note) }
        )

        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter

        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        notesList = db.getAllNotes().toMutableList()
        adapter.updateList(notesList)
        tvEmpty.visibility = if (notesList.isEmpty()) TextView.VISIBLE else TextView.GONE
    }

    private fun goToEdit(note: Note) {
        val i = Intent(this, AddEditNoteActivity::class.java)
        i.putExtra("note_id", note.id)
        startActivity(i)
    }

    private fun confirmDelete(note: Note) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar nota")
            .setMessage("¿Seguro que quieres eliminar esta nota?")
            .setPositiveButton("Eliminar") { _, _ ->
                db.deleteNote(note.id)
                loadNotes()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
