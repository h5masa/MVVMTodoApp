package com.masa.mvvmtodolist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.masa.mvvmtodolist.R;
import com.masa.mvvmtodolist.databinding.ActivityMainBinding;
import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.view.adapter.TodoRecyclerAdapter;
import com.masa.mvvmtodolist.view.checklistener.CustomCheckListener;
import com.masa.mvvmtodolist.view.clicklistener.CustomClickListener;
import com.masa.mvvmtodolist.viewmodel.TodoViewModel;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TodoViewModel viewModel;
    ActivityMainBinding binding;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selectall:
                viewModel.selectall(true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        final TodoRecyclerAdapter adapter = new TodoRecyclerAdapter(
                (todo) -> {
                    Log.d("Click Event", todo.getId() + " " + todo.getTitle() + " " + todo.getDetail());
                },
                (todo, checked) -> {
                    Log.d("Check Event", todo.getTitle() + " " + checked);
                    viewModel.select(todo);
                });


        viewModel.getAlldata().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                if (todos != null) {
                    if (!todos.isEmpty()) {
                        Log.d("MAIN", "Changed => " + String.valueOf(todos.get(todos.size() - 1).toString()));
                    }
                    adapter.setTodos(todos);
                    binding.rvTodos.setAdapter(adapter);
                }
            }
        });

        binding.fabAdd.setOnClickListener(this);
        binding.fabremove.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.fabAdd)) {
            Todo todo = new Todo(binding.editTodo.getText().toString(), "hi", false);
            viewModel.insert(todo);
            binding.editTodo.setText("");
        } else if (v.equals(binding.fabremove)) {
            viewModel.delete();
        }
    }
}
