package com.masa.mvvmtodolist.model.repository;

import android.util.Log;

import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.entity.Todo;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

public class TodoRepository {
    private LiveData<List<Todo>> todos;
    private TodoDao todoDao;

    public TodoRepository(TodoDao todoDao) {
        this.todoDao = todoDao;
        this.todos = todoDao.getAllTodos();
    }

    public LiveData<List<Todo>> getTodos() {
        return this.todos;
    }

    @WorkerThread
    public void insert(final Todo todo) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code to do the Insert request
                todoDao.insert(todo);
                Log.d("Insert Action", "Running on the thread: " + Thread.currentThread().getName().toString());
            }
        });
        thread.start();
    }

    @WorkerThread
    public void update(final Todo todo) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code to do the Insert request
                todoDao.update(todo);
                Log.d("Update Action", "Running on the thread: " + Thread.currentThread().getName().toString());
            }
        });
        thread.start();
    }
}
