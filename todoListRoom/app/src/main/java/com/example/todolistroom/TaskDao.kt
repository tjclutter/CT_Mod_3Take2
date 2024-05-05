package com.example.todolistroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT title FROM task WHERE Id = :id")
    fun getTitle(id: Int): String
    @Query("SELECT content FROM task WHERE Id = :Id")
    fun getContent(Id: Int): String
    @Insert
    fun insertTask(vararg id: Task)
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
    @Query("DELETE FROM task WHERE Id = :id")
    fun deleteTask(id: Int)


}