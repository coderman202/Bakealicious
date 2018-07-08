package com.coderman202.bakealicious;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.coderman202.bakealicious.idling_resource.SimpleIdlingResource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)

public class RecipeDetailsStepListTest {


    @Rule
    public IntentsTestRule<RecipeDetailsActivity> activityTestRule = new IntentsTestRule<>(RecipeDetailsActivity.class);

    @Test
    public void checkStepList_Intent() {
        onView(ViewMatchers.withId(R.id.steps_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        String activityName = StepsActivity.class.getName();
        SimpleIdlingResource idlingResource = new SimpleIdlingResource(activityName);
        IdlingRegistry.getInstance().register(idlingResource);
        intended(hasComponent(activityName));
    }
}
