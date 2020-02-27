package com.cleanup.todoc.datas;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class,Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase INSTANCE;
    //our app database object


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "TodocDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();



}
