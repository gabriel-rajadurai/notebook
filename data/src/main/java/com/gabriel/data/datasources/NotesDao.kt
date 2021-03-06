package com.gabriel.data.datasources

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gabriel.data.models.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note): Long // Insert operation returns the inserted Item's id

    @Query("Select * from notes_table ORDER BY createdAt DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Select * from notes_table WHERE id=:id")
    suspend fun getNoteById(id: Int): Note?

    @Delete
    suspend fun deleteNotes(vararg notes: Note): Int
}