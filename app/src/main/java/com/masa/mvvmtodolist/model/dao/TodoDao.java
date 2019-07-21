package com.masa.mvvmtodolist.model.dao;

import com.masa.mvvmtodolist.model.entity.Todo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoDao {

    @Query("SELECT * from todo_table order by done desc")
    LiveData<List<Todo>> getAllTodos();

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

}
