package com.cleanup.todoc.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class ProjectWithTasks {
    @Embedded
    public Project project;
    @Relation(
            parentColumn = "id",
            entityColumn = "projectId"
    )
    public List<Task> tasks;

}
