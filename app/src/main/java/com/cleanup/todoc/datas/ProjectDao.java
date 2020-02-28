package com.cleanup.todoc.datas;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.cleanup.todoc.model.Project;
import java.util.List;
import android.arch.persistence.room.Dao;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM projects")
    LiveData<List<Project>> getProjects();

    @Insert
    void insertProjects(Project... projects);


}
