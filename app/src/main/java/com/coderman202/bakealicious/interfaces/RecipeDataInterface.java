package com.coderman202.bakealicious.interfaces;

import com.coderman202.bakealicious.model.RecipeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface RecipeDataInterface {

    /**
     * Gets recipes
     *
     * @return list of {@link RecipeItem} objects
     */
    @GET("baking.json")
    Call<List<RecipeItem>> getRecipes();

}
