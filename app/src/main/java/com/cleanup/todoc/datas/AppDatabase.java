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


@Database(entities = {Project.class,Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public static volatile AppDatabase INSTANCE;

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();
    public abstract ProjectWithTasksDao projectWithTasksDao();


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "TodocDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("project_Id", 1L);
                contentValues.put("name", "C'est une tâche qui persiste");
                contentValues.put("creationTimestamp", 1581184803370L);
                db.insert("tasks", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}

