package com.masa.mvvmtodolist.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.masa.mvvmtodolist.R;
import com.masa.mvvmtodolist.databinding.TodoItemBinding;
import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.view.CustomClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private LayoutInflater inflater;
    private List<Todo> todos = null;
    private CustomClickListener customClickListener;


    public TodoRecyclerAdapter(CustomClickListener customClickListener) {
        this.customClickListener = customClickListener;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        TodoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.todo_item, parent, false);
        binding.setCallback(this.customClickListener); // set the click event listener through data binding
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.binding.tvTodo.setText(todos.get(position).getTitle());
        holder.binding.tvTodo.setOnClickListener((v) -> {
            holder.binding.getCallback().onClick(todos.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    /**
     * TodoViewHolder
     */
    static class TodoViewHolder extends RecyclerView.ViewHolder {
        final TodoItemBinding binding;

        public TodoViewHolder(TodoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
