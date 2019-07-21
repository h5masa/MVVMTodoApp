package com.masa.mvvmtodolist.model.db;


import android.content.Context;

import com.masa.mvvmtodolist.model.dao.TodoDao;
import com.masa.mvvmtodolist.model.entity.Todo;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    private static TodoRoomDatabase INSTANCE = null;

    public static TodoRoomDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (TodoRoomDatabase.class) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), TodoRoomDatabase.class, "todo_database")
                                /** allow queries on the main thread. (As a default, Cannot access database on the main thread since it may potentially lock the UI for a long period of time.)
                                 Don't do this on a real app! See PersistenceBasicSample for an example.*/
                                //.allowMainThreadQueries()
                                .build();
            }
        }
        return INSTANCE;
    }
}
