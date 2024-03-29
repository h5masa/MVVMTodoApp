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
        Thread thread = new Thread(() -> {
            //Insert request on the worker thread
            todoDao.insert(todo);
        });
        thread.start();
    }

    @WorkerThread
    public void update(final Todo todo) {
        Thread thread = new Thread(() -> {
            //code to do the Update request
            todoDao.update(todo);
        });
        thread.start();
    }


    @WorkerThread
    public void selectall(boolean ischecked) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code to do the Update request
                todoDao.selectAll(ischecked);
                Log.d("Update All Action", "Running on the thread: " + Thread.currentThread().getName().toString());
            }
        });
        thread.start();
    }

    @WorkerThread
    public void delete(final Todo todo) {
        Thread thread = new Thread(() -> {
            //code to do the Insert request
            todoDao.delete(todo);
        });
        thread.start();
    }
}
