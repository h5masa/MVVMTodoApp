package com.masa.mvvmtodolist.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.masa.mvvmtodolist.R;
import com.masa.mvvmtodolist.databinding.FragmentHomeBinding;
import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.view.adapter.TodoRecyclerAdapter;
import com.masa.mvvmtodolist.viewmodel.TodoViewModel;


import org.parceler.Parcels;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private TodoViewModel viewModel;
    private FragmentHomeBinding binding ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return binding.getRoot();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TodoRecyclerAdapter adapter = new TodoRecyclerAdapter(
                (todo) -> {
                    Log.d("Click Event", todo.getId() + " " + todo.getTitle() + " " + todo.getDetail());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("MyTodo", Parcels.wrap(todo));
                    Navigation.findNavController(view).navigate(R.id.showDetail, bundle);
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
