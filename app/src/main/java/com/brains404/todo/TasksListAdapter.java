package com.brains404.todo;

import android.content.Context;
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
        Task task = TaskList.get(position);
        taskName.setText(task.getName());
        taskDescription.setText(task.getDescription());

        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            removeTask(position);
            }
        }
        );
    return view ;
    }
    public void removeTask(int i){
        TaskList.remove(i);
        notifyDataSetChanged();
    }
}
