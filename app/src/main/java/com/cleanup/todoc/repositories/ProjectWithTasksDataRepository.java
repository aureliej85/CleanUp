package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.datas.ProjectWithTasksDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectWithTasks;

import java.util.List;

public class ProjectWithTasksDataRepository {

    private ProjectWithTasksDao projectWithTasksDao;

    public ProjectWithTasksDataRepository(ProjectWithTasksDao projectWithTasksDao){
        this.projectWithTasksDao = projectWithTasksDao;
    }

    public LiveData<List<ProjectWithTasks>> getProjectWithTasks(){
        return projectWithTasksDao.getProjectsWithTasks();
    }
}
