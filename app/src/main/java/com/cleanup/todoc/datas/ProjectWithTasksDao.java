package com.cleanup.todoc.datas;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.cleanup.todoc.model.ProjectWithTasks;
import java.util.List;

@Dao
public interface ProjectWithTasksDao {

    @Transaction
    @Query("SELECT * FROM projects")
    LiveData<List<ProjectWithTasks>> getProjectsWithTasks();

}
