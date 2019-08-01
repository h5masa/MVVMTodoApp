package com.masa.mvvmtodolist.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.masa.mvvmtodolist.model.entity.Todo;
import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.db.TodoRoomDatabase;
import com.masa.mvvmtodolist.model.repository.TodoRepository;

import java.util.List;

import androidx.annotation.NonNull;
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
        if(todo.getTitle().isEmpty()){
            Toast.makeText(getApplication(), "No Item to be added", Toast.LENGTH_SHORT).show();
        }else {
            this.todoRepository.insert(todo);
        }
    }


    public void update(final Todo todo) {
        this.todoRepository.update(todo);
    }

    public void selectall(boolean ischecked) {
        this.todoRepository.selectall(ischecked);
    }

    public void select(final Todo todo) {
        todo.setDone(!todo.getDone());
        update(todo);
    }

    public void delete() {
        List<Todo> todos = todoRepository.getTodos().getValue();
        int count = 0;
        for (Todo todo : todos) {
            if (todo.getDone()) {
                count++;
                this.todoRepository.delete(todo);
            }
        }

        if (todos.size() == 0)
            Toast.makeText(getApplication(), "No item to be deleted", Toast.LENGTH_SHORT).show();

        else if (count == 0) {
            Toast.makeText(getApplication(), "No selected item", Toast.LENGTH_SHORT).show();
        }

    }
}
