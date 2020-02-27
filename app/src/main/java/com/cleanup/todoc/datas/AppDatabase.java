package com.cleanup.todoc.datas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class,Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

}
