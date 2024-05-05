package com.example.todolistroom

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTaskButton = findViewById<FloatingActionButton>(R.id.addTaskButton)
        val deleteTaskButton = findViewById<FloatingActionButton>(R.id.deleteButton)
        val titleText = findViewById<TextView>(R.id.titleTodo)
        val list_view = findViewById<ListView>(R.id.listView)


        var taskList = mutableListOf<String>()
        var taskListRaw = listOf<Task>()

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "Tasks.db"
        ).build()
        Thread {
            taskListRaw = db.TaskDao().getAll()

            for (i in 1..taskListRaw.size) {
                var printTask = taskListRaw[i - 1].title + ": " + taskListRaw[i - 1].content


                taskList += printTask
            }






            var arrayAdapter: ArrayAdapter<*>
            arrayAdapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                taskList
            )



            runOnUiThread {
                list_view.adapter = arrayAdapter
            }
            }.start()

        var delete = false

        list_view.setOnItemClickListener{parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            val postion = taskList.indexOf(selectedItem)
            if("DONE" in taskList[position] && delete == false){
                taskList[position] = taskList[position].replace("DONE", "")
            }else if (delete == false) {
                taskList[position] += "   DONE"
            }else{
                for(i in taskListRaw){
                    if(i.title in taskList[position] && i.content in taskList[position]){
                        Thread {
                            db.TaskDao().deleteTask(i.Id)
                        }.start()
                    }
                }
                taskList.removeAt(position)

            }
            list_view.invalidateViews()


        }

        addTaskButton.setOnClickListener{

                var intent = Intent(this, addTask::class.java)
                startActivity(intent)

        }

        deleteTaskButton.setOnClickListener{
            if(delete == false) {
                delete = true
                titleText.text = "Delete"
            }else{
                delete = false
                titleText.text = "Todo List"
            }
        }






    }
}