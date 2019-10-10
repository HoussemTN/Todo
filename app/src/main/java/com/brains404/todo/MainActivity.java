package com.brains404.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    ListView tasksListView;
    List<Task> TasksList = new ArrayList<Task>();
    Button btn_addTask;
    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksListView = findViewById(R.id.tasksListView);
        prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
       // prefs.edit().clear().apply();
        /* TESTING*/
       /* Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }*/

        Gson gson = new Gson();
        // Get all Keys to loop
        Set<String> keys = prefs.getAll().keySet();
        // Case To/do List not empty
        if (keys.size() > 0) {
            Iterator<String> itr = keys.iterator();
            while(itr.hasNext()){
                String json = prefs.getString(itr.next(), "");
                Task myTask = gson.fromJson(json, Task.class);
                    TasksList.add(myTask);

            }
        }

        TasksListAdapter tasksListAdapter = new TasksListAdapter(this, R.layout.list_items, TasksList);
        tasksListView.setAdapter(tasksListAdapter);
        btn_addTask = findViewById(R.id.btn_addTask);
        btn_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TasksList.clear();
                Intent intent = new Intent(getApplicationContext(), addTaskActivity.class);
                startActivity(intent);
            }
        });

    }


}
