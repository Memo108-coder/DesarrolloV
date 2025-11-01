package com.example.temas2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private var notes: MutableList<Note>,
    private val onEdit: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvContent.text = if (note.content.length > 100) note.content.take(100) + "..." else note.content
        holder.tvDate.text = note.createdAt
        holder.btnEdit.setOnClickListener { onEdit(note) }
        holder.btnDelete.setOnClickListener { onDelete(note) }
    }

    override fun getItemCount(): Int = notes.size

    fun updateList(newNotes: List<Note>) {
        notes = newNotes.toMutableList()
        notifyDataSetChanged()
    }
}