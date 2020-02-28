package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.datas.AppDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideTaskDataSource(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static ProjectDataRepository provideProjectDataSource(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ProjectDataRepository projectDataSource = provideProjectDataSource(context);
        TaskDataRepository taskDataSource = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataSource, taskDataSource, executor);
    }


}
