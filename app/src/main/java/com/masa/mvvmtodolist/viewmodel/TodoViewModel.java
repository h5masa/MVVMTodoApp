package com.masa.mvvmtodolist.viewmodel;

import android.app.Application;
import android.util.Log;

import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.db.TodoRoomDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TodoViewModel extends AndroidViewModel {


    private TodoDao todoDao;
    private LiveData<List<Todo>> alldata;


    public TodoViewModel(@NonNull Application application) {
        super(application);
        //TodoRoomDatabase.getDatabase(application).todoDao().insert(new Todo("mytitle","lala",false));
        todoDao = TodoRoomDatabase.getDatabase(application).todoDao();
    }


    public LiveData<List<Todo>> getAlldata() {
        alldata = todoDao.getAllTodos();
        return alldata;
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
