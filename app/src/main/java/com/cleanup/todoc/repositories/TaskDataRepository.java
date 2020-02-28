package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.datas.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao){
        this.taskDao = taskDao;
    }


    public LiveData<List<Task>> getAllTasks(){
        return taskDao.getAllTasks();
    }

    public void createTask(Task task){
        taskDao.insertTask(task);
    }

    public void deleteTask(Task task){
        taskDao.deleteTask(task);
    }

    public void updateTask(Task task){
        taskDao.updateTask(task);
    }
}
