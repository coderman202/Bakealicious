package com.coderman202.bakealicious;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.coderman202.bakealicious.adapters.IngredientsListAdapter;
import com.coderman202.bakealicious.adapters.StepListAdapter;
import com.coderman202.bakealicious.idling_resource.SimpleIdlingResource;
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

    ActionBar recipeDetailsActionBar;


    //Testing code
    // The Idling Resource which will be null in production.
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource(this.getClass().getName());
        }
        return mIdlingResource;
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                recipeItem = intent.getParcelableExtra(RECIPE_ITEM_KEY);
            }
        }
    }

    private void initAppBar() {
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
