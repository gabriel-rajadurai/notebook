package com.gabriel.notebook.features.notesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.data.models.Note
import com.gabriel.notebook.common.DATE_FORMAT
import com.gabriel.notebook.common.MONTH_DATE_TIME_FORMAT
import com.gabriel.notebook.common.toFormat
import com.gabriel.notebook.databinding.ItemNoteBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(private val listener: NotesItemClickListener) :
    ListAdapter<Note, NotesListAdapter.NotesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }


    inner class NotesViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            fun String.toDate(inputFormat: String): Date? {
                val sdf = SimpleDateFormat(inputFormat, Locale.getDefault())
                return try {
                    sdf.parse(this)
                } catch (e: ParseException) {
                    e.printStackTrace()
                    null
                }
            }
            binding.note = note.apply {
                createdAt = createdAt.toDate(DATE_FORMAT)?.toFormat(MONTH_DATE_TIME_FORMAT) ?: "N.A"
            }
            binding.root.setOnClickListener {
                listener.onItemClicked(note)
            }
        }
    }

    interface NotesItemClickListener {
        fun onItemClicked(note: Note)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}