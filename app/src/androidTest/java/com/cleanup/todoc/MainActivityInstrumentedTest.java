package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.cleanup.todoc.datas.AppDatabase;
import com.cleanup.todoc.ui.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.cleanup.todoc.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Gaëtan HERFRAY
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    // FOR DATA
    private AppDatabase database;


    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

   @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
    }


    @Test
    public void addAndRemoveTask()  {
        MainActivity activity = rule.getActivity();
        TextView lblNoTask = activity.findViewById(R.id.lbl_no_task);
        RecyclerView listTasks = activity.findViewById(R.id.list_tasks);

        int tasks = listTasks.getAdapter().getItemCount();

        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(replaceText("Tâche example"));
        onView(withId(android.R.id.button1)).perform(click());

        // Check that lblTask is not displayed anymore
        assertThat(lblNoTask.getVisibility(), equalTo(View.GONE));
        // Check that recyclerView is displayed
        assertThat(listTasks.getVisibility(), equalTo(View.VISIBLE));
        // Check that it contains one element only
        assertThat(listTasks.getAdapter().getItemCount(), equalTo(tasks + 1));

        onView(withRecyclerView(R.id.list_tasks).atPositionOnView(0, R.id.img_delete))
                .perform(click());

        assertThat(listTasks.getAdapter().getItemCount(), equalTo(tasks));
    }

    @Test
    public void sortTasks() {
        MainActivity activity = rule.getActivity();
        RecyclerView listTasks = activity.findViewById(R.id.list_tasks);
        int tasks = listTasks.getAdapter().getItemCount();

        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(replaceText("aaa Tâche example"));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(replaceText("zzz Tâche example"));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.txt_task_name)).perform(replaceText("hhh Tâche example"));
        onView(withId(android.R.id.button1)).perform(click());

        assertThat(listTasks.getAdapter().getItemCount(), equalTo(tasks + 3));

        onView(withId(R.id.list_tasks)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.list_tasks).atPosition(1)).check(matches(hasDescendant(withText("aaa Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(2)).check(matches(hasDescendant(withText("zzz Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(3)).check(matches(hasDescendant(withText("hhh Tâche example"))));


        // Sort alphabetical
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_alphabetical)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPosition(1))
                .check(matches(hasDescendant(withText("aaa Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(2))
        .check(matches(hasDescendant(withText("hhh Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(3))
                .check(matches(hasDescendant(withText("zzz Tâche example"))));

        // Sort alphabetical inverted
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_alphabetical_invert)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPosition(0))
                .check(matches(hasDescendant(withText("zzz Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(1))
                .check(matches(hasDescendant(withText("hhh Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(2))
                .check(matches(hasDescendant(withText("aaa Tâche example"))));

        // Sort old first
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_oldest_first)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPosition(1))
                .check(matches(hasDescendant(withText("aaa Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(2))
                .check(matches(hasDescendant(withText("zzz Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(3))
                .check(matches(hasDescendant(withText("hhh Tâche example"))));

        // Sort recent first
        onView(withId(R.id.action_filter)).perform(click());
        onView(withText(R.string.sort_recent_first)).perform(click());
        onView(withRecyclerView(R.id.list_tasks).atPosition(0))
                .check(matches(hasDescendant(withText("hhh Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(1))
                .check(matches(hasDescendant(withText("zzz Tâche example"))));
        onView(withRecyclerView(R.id.list_tasks).atPosition(2))
                .check(matches(hasDescendant(withText("aaa Tâche example"))));
    }
}
