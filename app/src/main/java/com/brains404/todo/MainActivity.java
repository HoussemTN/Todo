package com.brains404.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView  tasksListView ;
    List<Task> TasksList = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksListView=(ListView) findViewById(R.id.tasksListView);
        TasksList.add(new Task("Todo1","Todo1-Description","0"));
        TasksList.add(new Task("Todo2","Todo2-Description","0"));
        TasksList.add(new Task("Todo3","Todo3-Description","0"));
        TasksList.add(new Task("Todo4","Todo4-Description","0"));
        TasksListAdapter tasksListAdapter = new TasksListAdapter(this,R.layout.list_items,TasksList);
      tasksListView.setAdapter(tasksListAdapter);
    }
}
