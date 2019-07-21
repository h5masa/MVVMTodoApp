package com.masa.mvvmtodolist.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "details")
    private String detail;
    @ColumnInfo(name = "done")
    private Boolean done;

    public Todo(String title, String detail, Boolean done) {
        this.title = title;
        this.detail = detail;
        this.done = done;
    }

    @Override
    public String toString() {
        return "[ id = " + id + ", title = " + title + "]";
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Boolean getDone() {
        return done;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
