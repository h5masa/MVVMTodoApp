package com.masa.mvvmtodolist.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.masa.mvvmtodolist.R;
import com.masa.mvvmtodolist.databinding.FragmentDetailBinding;
import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.viewmodel.TodoViewModel;

import org.parceler.Parcels;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private Todo todo;
    private TodoViewModel viewModel;
    public ObservableField<Todo> todoObservableField = new ObservableField<>();


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        viewModel.getAlldata();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setDisplayfragment(this);
        todo = (Todo) Parcels.unwrap(getArguments().getParcelable("MyTodo"));
        todoObservableField.set(todo);


        Log.d("HI", todoObservableField.get().getTitle());


        binding.saveButton.setOnClickListener(it -> {
                    todo.setTitle(binding.addTaskTitleEditText.getText().toString());
                    todo.setDetail(binding.addTaskDescriptionEditText.getText().toString());
                    viewModel.update(todo);
                    Toast.makeText(getContext(), "Note Has Been Saved!", Toast.LENGTH_SHORT).show();

                }
        );
    }
}
