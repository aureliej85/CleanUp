package com.cleanup.todoc.datas;

import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.content.Context;
import javax.security.auth.callback.Callback;
import android.support.annotation.NonNull;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context ctxt) {
        this.context = ctxt;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(ctxt, AppDatabase.class, "TodocApp").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (instance == null) {
            instance = new DatabaseClient(mCtx);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }





}
