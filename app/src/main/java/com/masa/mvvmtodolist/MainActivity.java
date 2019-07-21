package com.masa.mvvmtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.masa.mvvmtodolist.databinding.ActivityMainBinding;
import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.viewmodel.TodoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TodoViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);


        viewModel.getAlldata().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                if (todos.size() > 0)
                    Log.d("MAIN", "Inserted => " + String.valueOf(todos.get(todos.size() - 1).toString()));

            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Main","Click") ;
                viewModel.insert(new Todo("customtitle", "hi", false));
            }
        });

    }
}
