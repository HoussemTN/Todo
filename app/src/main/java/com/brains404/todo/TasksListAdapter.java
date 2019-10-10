package com.brains404.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TasksListAdapter extends ArrayAdapter<Task> {
    Context context ;
    int resource ;
    List<Task> TaskList ;
   SharedPreferences prefs ;

    public TasksListAdapter(@NonNull Context context, int resource, @NonNull List<Task> TaskList) {
        super(context, resource, TaskList);
        this.context=context;
        this.resource=resource;
        this.TaskList=TaskList ;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_items,null);
        TextView taskName = view.findViewById(R.id.taskName);
        TextView taskDescription = view.findViewById(R.id.taskDescription);
        final Task task = TaskList.get(position);
        taskName.setText(task.getName());
        taskDescription.setText(task.getDescription());
        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            removeTask(position,task.getKey());
            }
        }
        );
    return view ;
    }
    public void removeTask(int position,String key){

        TaskList.remove(position);
        prefs = getContext().getSharedPreferences("prefs",Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
      notifyDataSetChanged();
    }
}
