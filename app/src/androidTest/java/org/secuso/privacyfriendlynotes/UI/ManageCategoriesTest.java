package org.secuso.privacyfriendlynotes.UI;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secuso.privacyfriendlynotes.ui.manageCategories.ManageCategoriesActivity;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.secuso.privacyfriendlynotes.R;
import org.secuso.privacyfriendlynotes.ui.notes.TextNoteActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ManageCategoriesTest {


    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(ManageCategoriesActivity.class);

    public void addButton(){
        onView(withId(R.id.btn_add))
                .perform(click());
    }
    //Create a Categorie
    //T1 Testfall
    @Test
    public void createNotizenCategorie(){
        onView(withId(R.id.etName)).check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("Notizen"));
        addButton();
    }
    // Create a Categorie named Checklisten
    @Test
    public void createCheckList(){
        onView(withId(R.id.etName)).check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("Checklisten"));
        addButton();
    }
    //create a categorie names Skizzen
    @Test
    public void createSkizzen(){
        onView(withId(R.id.etName)).check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("Skizzen"));
        addButton();
    }
    //Check if the Items added the the RecyclerView are displayed
    @Test(expected = PerformException.class)
    public void checkListViewElements(){
        onView(withId(R.id.recyclerview_category))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("Notizen")).check(matches(isDisplayed()));
        onView(withText("Checklisten")).check(matches(isDisplayed()));
        onView(withText("Skizzen")).check(matches(isDisplayed()));
    }






}
