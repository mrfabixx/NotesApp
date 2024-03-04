package org.secuso.privacyfriendlynotes.UI;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.secuso.privacyfriendlynotes.R;
import org.secuso.privacyfriendlynotes.ui.notes.TextNoteActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestNoteActivityTest {

    public String TEST_STRING = "Notiz Input";


    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(TextNoteActivity.class);

    // T2 Testfall
    @Test
    public void test1_enterText() {
        onView(withId(R.id.etName))
                .check(matches(isDisplayed()))
                .perform(clearText())
                .perform(click())
                .perform(typeText(TEST_STRING));
        closeSoftKeyboard();
    }

    @Test
    public void test2_categorie() {
        onView(withId(R.id.spinner_category)).check(matches(isDisplayed()));

    }

    @Test
    public void test3_actionButton() {
        onView(withId(R.id.fab_menu)).check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.btn_bold))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
        onView(withId(R.id.btn_italics))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
        onView(withId(R.id.btn_underline))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
    }

    // Speichern des Task
    @Test
    public void test4_SaveTask() {
        onView(withId(R.id.action_save)).check(matches(isDisplayed()))
                .check(matches(isClickable()))
                .perform(click());
    }
//Testfall T4
    @Test
    public void test5_checkListAfterSaveTask() {
        onView(withId(R.id.recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Log.i("RecylerView", "RecylerView is visible ");
        onView(withId(R.id.recycler_view))
                .perform(scrollTo()).check(matches(withText("Das ist eine Notiz")));
    }
//    @Test(expected = PerformException.class)
//    public void recyclerViewTest(){
//        onView(withId(R.id.recycler_view))
//                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//                // scrollTo wird den Test fehlschlagen lassen, wenn kein Element übereinstimmt
//                .perform(scrollTo(hasDescendant(withText("nicht in der Liste"))));
//    }








}
