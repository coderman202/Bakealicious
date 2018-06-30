package com.coderman202.bakealicious;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.coderman202.bakealicious.adapters.RecipeListAdapter;
import com.coderman202.bakealicious.builders.ApiUrlBuilder;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();
        loadRecipeList();
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
        Log.e(LOG_TAG, "LoadRecipeList");

        try{
            RecipeDataInterface recipeDataInterface =
                    ApiUrlBuilder.getRetrofitClient(this).create(RecipeDataInterface.class);

            Log.e(LOG_TAG, recipeDataInterface.toString());

            final List<RecipeItem> recipeItemList = new ArrayList<>();

            final Call<List<RecipeItem>> recipeListCall =
                    recipeDataInterface.getRecipes();
            recipeListCall.enqueue(new Callback<List<RecipeItem>>() {
                @Override
                public void onResponse(Call<List<RecipeItem>> call, Response<List<RecipeItem>> response) {
                    if(response.isSuccessful()){
                        Log.e(LOG_TAG, "Response Length: " + response.body().toString());
                        recipeItemList.addAll(response.body());
                        Log.e(LOG_TAG, recipeItemList.toString());
                        recipeListAdapter.setRecipeList(recipeItemList);
                    }
                }

                @Override
                public void onFailure(Call<List<RecipeItem>> call, Throwable t) {
                    t.printStackTrace();
                    Log.e(LOG_TAG, "Fail");

                }
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
