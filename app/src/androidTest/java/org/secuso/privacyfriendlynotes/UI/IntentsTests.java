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
import org.secuso.privacyfriendlynotes.ui.notes.TextNoteActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentsTests {

    //T7 Testfall
    @Test
    public void splashActivityToMainActivityIntentTest() {
        // Starte SplashActivity
        Intents.init();
        ActivityScenario.launch(SplashActivity.class);

        // Überprüfen ob ein Intent zur MainActivity gesendet wurde
        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navigationtoTextNoteActivity() {
        ActivityScenario.launch(org.secuso.privacyfriendlynotes.ui.main.MainActivity.class);
        onView(withId(R.id.fab_expand_menu_button))
                .check(matches(isDisplayed())).perform(click());
        Log.i("displayed", "menu button is displayed");

        onView(withId(R.id.fab_text)).check(matches(isDisplayed()))
                .perform(click());
        intended(hasComponent(TextNoteActivity.class.getName()));

    }

    @Test
    public void navigationToChecklistNoteActivity() {

    }

    @Test
    public void navigationToAudioAcitvity() {

    }

    @Test
    public void navigationToSketchActivity() {

    }


}
