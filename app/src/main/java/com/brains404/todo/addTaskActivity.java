package com.brains404.todo;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;


public class addTaskActivity extends AppCompatActivity {
    private Button add;
    private EditText newName;
    private EditText newDescription;
    SharedPreferences prefs;
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        newName=(EditText) findViewById(R.id.newTaskName);
        newDescription=(EditText) findViewById(R.id.newTaskDescription);
        prefs = this.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        add=(Button) findViewById(R.id.addNewTask);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Task newTask = new Task(newName.getText().toString(),newDescription.getText().toString(),"Task-"+prefs.getAll().size(),0);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                Gson gson = new Gson();
                json= gson.toJson(newTask);
                prefsEditor.putString("Task-"+prefs.getAll().size(), json);
                prefsEditor.apply();
               Toast.makeText(getApplicationContext(),"Task Added Successfully",Toast.LENGTH_SHORT).show();
                newName.getText().clear();
                newDescription.getText().clear();
            }
        });

    }
}
