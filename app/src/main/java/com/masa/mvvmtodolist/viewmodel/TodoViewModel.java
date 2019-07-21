package com.masa.mvvmtodolist.viewmodel;

import android.app.Application;

import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.db.TodoRoomDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TodoViewModel extends AndroidViewModel {

    private TodoDao todoDao ;
    private LiveData<List<Todo>> alldata;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        //TodoRoomDatabase.getDatabase(application).todoDao().insert(new Todo("mytitle","lala",false));
        todoDao = TodoRoomDatabase.getDatabase(application).todoDao();
        alldata = todoDao.getAllTodos();
    }

    public LiveData<List<Todo>> getAlldata(){
        return alldata;
    }

    public void insert(Todo todo){
        todoDao.insert(todo);
    }

}
