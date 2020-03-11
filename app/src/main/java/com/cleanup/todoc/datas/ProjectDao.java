package com.cleanup.todoc.datas;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectWithTasks;
import java.util.List;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Transaction;

@Dao
public interface ProjectDao {

    @Transaction
    @Query("SELECT * FROM projects")
    LiveData<List<ProjectWithTasks>> getProjects();

    @Insert
    void insertProjects(Project... projects);


}
