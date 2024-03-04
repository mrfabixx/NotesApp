package org.secuso.privacyfriendlynotes.UI;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.android.material.tabs.TabLayout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secuso.privacyfriendlynotes.R;
import org.secuso.privacyfriendlynotes.ui.SplashActivity;
import org.secuso.privacyfriendlynotes.ui.notes.AudioNoteActivity;
import org.secuso.privacyfriendlynotes.ui.notes.ChecklistNoteActivity;
import org.secuso.privacyfriendlynotes.ui.notes.SketchActivity;
import org.secuso.privacyfriendlynotes.ui.notes.TextNoteActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentsTests {

 //   T6 Testfall
    @Test
    public void navigationtoTextNoteActivity() {
        // Intents Initialisierung
        Intents.init();
        // Spezifische Aktivität wird gelaunched
        ActivityScenario.launch(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed())).perform(click());
        Log.i("displayed", "menu button is displayed");

        onView(withId(R.id.fab_text)).check(matches(isDisplayed()))
                .perform(click());
        //Validierung des Intents
        intended(hasComponent(TextNoteActivity.class.getName()));
        // Espresso-Intents nach jedem Test aufräumen
        Intents.release();
    }

    @Test
    public void navigationToChecklistNoteActivity() {
        // Intents Initialisierung
        Intents.init();
        ActivityScenario.launch(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed())).perform(click());
        Log.i("displayed", "menu button is displayed");

        onView(withId(R.id.fab_checklist)).check(matches(isDisplayed()))
                .perform(click());
        intended(hasComponent(ChecklistNoteActivity.class.getName()));
        // Espresso-Intents nach jedem Test aufräumen
        Intents.release();
    }


    @Test
    public void navigationToAudioAcitvity() {
        // Intents Initialisierung
        Intents.init();
        ActivityScenario.launch(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed())).perform(click());
        Log.i("displayed", "menu button is displayed");

        onView(withId(R.id.fab_audio)).check(matches(isDisplayed()))
                .perform(click());
        intended(hasComponent(AudioNoteActivity.class.getName()));
        // Espresso-Intents nach jedem Test aufräumen
        Intents.release();

    }

    @Test
    public void navigationToSketchActivity() {
        // Intents Initialisierung
        Intents.init();
        ActivityScenario.launch(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed())).perform(click());
        Log.i("displayed", "menu button is displayed");

        onView(withId(R.id.fab_sketch)).check(matches(isDisplayed()))
                .perform(click());
        intended(hasComponent(SketchActivity.class.getName()));
        // Espresso-Intents nach jedem Test aufräumen
        Intents.release();

    }


}
