package com.example.todolistroom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.todolistroom.ui.theme.TodoListRoomTheme
import com.google.android.material.textfield.TextInputEditText

class addTask : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        var addButton = findViewById<Button>(R.id.addTaskButon)
        var viewButton = findViewById<Button>(R.id.viewTaskButton)
        var titleEdit = findViewById<TextInputEditText>(R.id.titleEdit)
        var contentEdit = findViewById<TextInputEditText>(R.id.contentEdit)
        var title = findViewById<TextView>(R.id.textView3)


        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "Tasks.db"
        ).build()


        addButton.setOnClickListener{
            Thread{
                db.TaskDao().insertTask(
                    Task(
                        0,
                        titleEdit.text.toString(),
                        contentEdit.text.toString()
                    )
                )
                runOnUiThread {
                    titleEdit.text?.clear()
                    contentEdit.text?.clear()
                }
            }.start()


            //titleEdit.text?.clear()
            //contentEdit.text?.clear()
        }
        viewButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}