package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.ProjectWithTasks;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.ProjectWithTasksDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TodocViewModel extends android.arch.lifecycle.ViewModel {

    //REPOSITORIES
    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final ProjectWithTasksDataRepository projectWithTasksDataSource;
    private final Executor executor;

    //DATA
    @Nullable
    private LiveData<List<ProjectWithTasks>> currentProject;

    public TodocViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, ProjectWithTasksDataRepository projectWithTasksDataSource, Executor executor){
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.projectWithTasksDataSource = projectWithTasksDataSource;
        this.executor = executor;
    }


    public void init(){
        if (this.currentProject == null){
            currentProject = projectWithTasksDataSource.getProjectWithTasks();
        }
    }


    // FOR PROJECT
    @Nullable
    public LiveData<List<ProjectWithTasks>> getProjects(){ return this.currentProject;}


    // FOR TASKS

    public LiveData<List<Task>> getTasks(){
        return taskDataSource.getAllTasks();
    }

    public void createTask(Task task){
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(Task task){
        executor.execute(() -> {
            taskDataSource.deleteTask(task);
        });
    }

    public void updateTask(Task task){
        executor.execute(() -> {
            taskDataSource.updateTask(task);
        });
    }

    // FOR PROJECT WITH TASKS

    public LiveData<List<ProjectWithTasks>> getProjectWithTasks(){ return projectWithTasksDataSource.getProjectWithTasks();}


}
