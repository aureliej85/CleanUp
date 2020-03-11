package com.cleanup.todoc.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.ProjectWithTasksDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TodocViewModel;
import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final ProjectWithTasksDataRepository projectWithTasksDataSource;
    private final Executor executor;

    public ViewModelFactory(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, ProjectWithTasksDataRepository projectWithTasksDataSource, Executor executor){
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.projectWithTasksDataSource = projectWithTasksDataSource;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TodocViewModel.class)) {
            return (T) new TodocViewModel(projectDataSource, taskDataSource, projectWithTasksDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
