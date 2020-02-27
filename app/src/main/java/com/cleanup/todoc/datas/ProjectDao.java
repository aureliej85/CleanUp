package com.cleanup.todoc.datas;

import android.arch.persistence.room.Query;
import com.cleanup.todoc.model.Project;
import java.util.List;
import android.arch.persistence.room.Dao;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM projects")
    List<Project> getProjects();


}
