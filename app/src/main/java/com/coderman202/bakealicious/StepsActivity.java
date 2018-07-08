package com.coderman202.bakealicious;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.coderman202.bakealicious.adapters.StepPagerAdapter;
import com.coderman202.bakealicious.model.RecipeItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    private static final String LOG_TAG = StepsActivity.class.getSimpleName();

    private static final String RECIPE_ITEM_KEY = "recipe_item";
    private static final String STEP_POSITION_KEY = "position_key";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    StepPagerAdapter stepPagerAdapter;

    FragmentManager stepFragmentManager;

    private RecipeItem recipeItem;
    private int stepPosition;

    ActionBar stepActionBar;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.step_container) ViewPager stepViewPager;

    @BindView(R.id.step_tablayout) TabLayout stepTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        ButterKnife.bind(this);

        recipeItem = getIntent().getParcelableExtra(RECIPE_ITEM_KEY);
        stepPosition = getIntent().getIntExtra(STEP_POSITION_KEY, 0);

        initAppBar();
        initStepViewPager();
    }

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    private void initStepViewPager() {
        stepFragmentManager = getSupportFragmentManager();
        stepPagerAdapter = new StepPagerAdapter(stepFragmentManager, recipeItem);
        stepViewPager.setAdapter(stepPagerAdapter);
        stepTabLayout.setupWithViewPager(stepViewPager);
        stepViewPager.setCurrentItem(stepPosition);
    }

    private void initAppBar() {
        stepActionBar = getSupportActionBar();
        stepActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed(){
        Log.e(LOG_TAG, "hey");
        Intent intent = new Intent();
        intent.putExtra(RECIPE_ITEM_KEY, recipeItem);
        setResult(RESULT_OK, intent);
        finish();
    }
}
