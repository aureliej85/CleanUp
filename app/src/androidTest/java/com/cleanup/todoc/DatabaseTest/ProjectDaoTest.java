package com.cleanup.todoc.DatabaseTest;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.datas.AppDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectWithTasks;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    // FOR DATA
    private AppDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }



    // DATA SET FOR TEST
    private Project[] projects = Project.getAllProjects();



    @Test
    public void getProjects() throws InterruptedException {
        this.database.projectDao().insertProjects(projects);
        List<ProjectWithTasks> projectsList = LiveDataTestUtil.getValue(this.database.projectDao().getProjects());

        assertEquals(3, projectsList.size());
        assertEquals("Projet Tartampion", projectsList.get(0).project.getName());
        assertEquals("Projet Lucidia", projectsList.get(1).project.getName());
        assertEquals("Projet Circus", projectsList.get(2).project.getName());
    }


}
