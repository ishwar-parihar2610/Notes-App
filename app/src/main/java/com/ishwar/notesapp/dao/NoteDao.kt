package com.ishwar.notesapp.dao

import androidx.room.*
import com.ishwar.notesapp.entities.Notes

@Dao
interface NoteDao {

    @Query("Select * From notes ORDER BY id DESC")
   suspend fun  getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)


}