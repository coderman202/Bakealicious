package com.coderman202.bakealicious;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coderman202.bakealicious.adapters.StepPagerAdapter;
import com.coderman202.bakealicious.model.RecipeItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {


    // Step
    private static final String RECIPE_ITEM_KEY = "Recipe1";
    private static final String STEP_POSITION_KEY = "Position_key";

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

    @BindView(R.id.step_toolbar) Toolbar stepToolbar;
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
    }

    private void initAppBar() {
        setSupportActionBar(stepToolbar);
        stepActionBar = getSupportActionBar();
        stepActionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
