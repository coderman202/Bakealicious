package com.coderman202.bakealicious;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.coderman202.bakealicious.adapters.IngredientsListAdapter;
import com.coderman202.bakealicious.adapters.StepListAdapter;
import com.coderman202.bakealicious.model.RecipeItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeDetailsActivity.class.getSimpleName();

    private static final String RECIPE_ITEM_KEY = "Recipe";

    @BindView(R.id.ingredients_title) TextView ingredientsTitleView;
    @BindView(R.id.steps_title) TextView stepsTitleView;

    @BindView(R.id.ingredients_list) RecyclerView ingredientsListView;
    @BindView(R.id.steps_list) RecyclerView stepsListView;

    LinearLayoutManager ingredientsListLayoutManager;
    LinearLayoutManager stepsListLayoutManager;

    IngredientsListAdapter ingredientsListAdapter;
    StepListAdapter stepListAdapter;

    RecipeItem recipeItem;

    @BindView(R.id.recipe_details_toolbar) Toolbar recipeDetailsToolbar;
    ActionBar recipeDetailsActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ButterKnife.bind(this);

        if(savedInstanceState == null){
            recipeItem = getIntent().getParcelableExtra(RECIPE_ITEM_KEY);
        } else {
          recipeItem = savedInstanceState.getParcelable(RECIPE_ITEM_KEY);
        }

        initAppBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRecyclerViews();
        loadBothLists();
        populateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_ITEM_KEY, recipeItem);
    }

    // Handling screen rotation
    @Override
    public void onRestoreInstanceState(Bundle inState){
        recipeItem = inState.getParcelable(RECIPE_ITEM_KEY);
    }

    private void initAppBar() {
        setSupportActionBar(recipeDetailsToolbar);
        recipeDetailsActionBar = getSupportActionBar();
        recipeDetailsActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void populateUI(){
        String ingredientsTitle = this.getString(R.string.ingredients_title, String.valueOf(recipeItem.getIngredientsCount()));
        ingredientsTitleView.setText(ingredientsTitle);

        String stepsTitle = this.getString(R.string.steps_title, String.valueOf(recipeItem.getStepsCount()));
        stepsTitleView.setText(stepsTitle);
    }

    /**
     * Method to initialise the recycler views and set both layout managers and adapters
     */
    private void initRecyclerViews() {
        setListLayouts();

        ingredientsListAdapter = new IngredientsListAdapter(this);
        ingredientsListView.setAdapter(ingredientsListAdapter);

        stepListAdapter = new StepListAdapter(this);
        stepsListView.setAdapter(stepListAdapter);
    }

    /**
     * A method to set the layouts of both {@link RecyclerView} lists.
     */
    private void setListLayouts() {
        ingredientsListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ingredientsListView.setLayoutManager(ingredientsListLayoutManager);

        stepsListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stepsListView.setLayoutManager(stepsListLayoutManager);
    }

    /**
     * Loads both {@link RecyclerView} adapters with the appropriate data
     */
    private void loadBothLists(){
        ingredientsListAdapter.setIngredientList(recipeItem.getIngredients());
        stepListAdapter.setRecipeItem(recipeItem);
    }
}
