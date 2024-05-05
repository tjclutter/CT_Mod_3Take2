package com.example.todolistroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun TaskDao(): TaskDao
}