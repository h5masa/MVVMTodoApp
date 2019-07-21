package com.masa.mvvmtodolist.viewmodel;

import android.app.Application;
import android.util.Log;

import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.db.TodoRoomDatabase;
import com.masa.mvvmtodolist.model.repository.TodoRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TodoViewModel extends AndroidViewModel {

    private TodoDao todoDao;
    private TodoRepository todoRepository;


    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoDao = TodoRoomDatabase.getDatabase(application).todoDao();
    }


    public LiveData<List<Todo>> getAlldata() {
        todoRepository = new TodoRepository(todoDao);
        return todoRepository.getTodos();
    }


    public void insert(final Todo todo) {
        this.todoRepository.insert(todo);
    }

    @WorkerThread
    public void update(final Todo todo) {
        this.todoRepository.update(todo);
    }


}
