package com.masa.mvvmtodolist.view.checklistener;

import com.masa.mvvmtodolist.model.entity.Todo;

public interface CustomCheckListener {

    void onChecked(Todo todo, boolean ischecked);
}
