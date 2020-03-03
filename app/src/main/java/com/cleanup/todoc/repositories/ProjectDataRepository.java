package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.datas.ProjectDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectWithTasks;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

    public LiveData<List<ProjectWithTasks>> getProjects(){
        return projectDao.getProjects();
    }
}
