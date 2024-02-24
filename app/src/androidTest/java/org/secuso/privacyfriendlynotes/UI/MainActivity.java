package org.secuso.privacyfriendlynotes.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secuso.privacyfriendlynotes.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivity {

    @Rule
     public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);


    @Test
    public void MenuLayout(){
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//                .perform(click());

    }
    @Test
    public void addtask(){
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed()))
                .perform(click());

    }
    @Test
    public void openfloatingmenu(){
        onView(withId(R.id.fab_menu))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void Menuobjekts(){
        onView(withId(R.id.fab_sketch)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_audio)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_checklist)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_text)).check(matches(isDisplayed()));


    }
    @Test
    public void newChecklist(){
        onView(withId(R.id.fab_checklist)).check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.etName))
                .perform(typeText("Checklist 1 "));
        onView(withId(R.id.etNewItem))
                .perform(click())
                .perform(typeText("Item 1"));
        onView(withId(R.id.btn_add))
                .check(matches(isClickable()))
                .perform(click());
    }
    @Test
    public void Save(){
        onView(withId(R.id.action_save)).check(matches(isDisplayed()))
                .check(matches(isClickable()))
                .perform(click());
    }
    @Test
    public void newSketch(){
        addtask();
        onView(withId(R.id.fab_sketch))
                .perform(click());
        onView(withId(R.id.etName))
                .perform(click())
                .perform(clearText())
                .perform(typeText("Sketch 1 "));
        onView(withId(R.id.btn_color_selector))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
        Save();
    }







}
