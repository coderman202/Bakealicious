package com.coderman202.bakealicious;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.coderman202.bakealicious.adapters.RecipeListAdapter;
import com.coderman202.bakealicious.builders.ApiUrlBuilder;
import com.coderman202.bakealicious.idling_resource.SimpleIdlingResource;
import com.coderman202.bakealicious.interfaces.RecipeDataInterface;
import com.coderman202.bakealicious.model.RecipeItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recipe_list) RecyclerView recipeListView;

    // Recipe List Adapter for displaying all recipes in the above Recycler view
    RecipeListAdapter recipeListAdapter;

    // Layout Manager for the Recipe List Adapter above
    GridLayoutManager recipeListLayoutManager;

    // Key for saving the scroll position of the RecyclerView.
    public static final String BUNDLE_RECYCLER_LAYOUT_KEY = "RecyclerView Layout";

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
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();
        loadRecipeList();
    }

    // Handling screen rotations.
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT_KEY, recipeListLayoutManager.onSaveInstanceState());
    }

    // Handling screen rotations.
    @Override
    public void onRestoreInstanceState(Bundle inState){
        Parcelable savedRecyclerLayoutState = inState.getParcelable(BUNDLE_RECYCLER_LAYOUT_KEY);
        recipeListLayoutManager.onRestoreInstanceState(savedRecyclerLayoutState);
    }

    /**
     * Method to initialise the recycler view and set both layout manager and adapter
     */
    private void initRecyclerView() {
        setRecipeListLayout();
        recipeListAdapter = new RecipeListAdapter(this);
        recipeListView.setAdapter(recipeListAdapter);
    }

    /**
     * A method to set the layout of the GridLayout, calling the method to check the screen width.
     */
    private void setRecipeListLayout() {
        recipeListLayoutManager = new GridLayoutManager(this, numberOfColumns());
        recipeListView.setLayoutManager(recipeListLayoutManager);
        recipeListView.setHasFixedSize(true);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // Changing this divider adjusts the width of each recipe card
        int widthDivider = 500;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 1; //to keep the grid aspect so there are always at least 2 columns
        return nColumns;
    }



    private void loadRecipeList(){

        try{
            RecipeDataInterface recipeDataInterface =
                    ApiUrlBuilder.getRetrofitClient(this).create(RecipeDataInterface.class);

            final List<RecipeItem> recipeItemList = new ArrayList<>();

            final Call<List<RecipeItem>> recipeListCall =
                    recipeDataInterface.getRecipes();
            recipeListCall.enqueue(new Callback<List<RecipeItem>>() {
                @Override
                public void onResponse(Call<List<RecipeItem>> call, Response<List<RecipeItem>> response) {
                    if(response.isSuccessful()){
                        recipeItemList.addAll(response.body());
                        recipeListAdapter.setRecipeList(recipeItemList);
                    }
                }

                @Override
                public void onFailure(Call<List<RecipeItem>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}