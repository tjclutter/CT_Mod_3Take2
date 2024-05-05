package com.example.todolistroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var Id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
)
