package com.coderman202.bakealicious.response;


import com.coderman202.bakealicious.model.RecipeItem;

import java.util.List;

/**
 * A class which represents lists of {@link com.coderman202.bakealicious.model.RecipeItem} objects
 * which are returned by the GET request
 */
public class RecipeJsonResponse {

    //@SerializedName("total_results")
    private int resultsCount;

    //@SerializedName("results")
    private List<RecipeItem> recipeList;


    /**
     * Instantiates a new RecipeJsonResponse.
     *
     * @param recipeList    the list of recipes
     */
    public RecipeJsonResponse(List<RecipeItem> recipeList) {
        this.recipeList = recipeList;
    }

    /**
     * Instantiates a new Recipe json response.
     * Empty constructor
     */
    public RecipeJsonResponse() {
    }


    /**
     * Gets results count.
     *
     * @return the results count
     */
    public int getResultsCount() {
        return resultsCount;
    }

    /**
     * Sets results count.
     *
     * @param resultsCount the results count
     */
    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }


    /**
     * Gets recipe list.
     *
     * @return the recipe list
     */
    public List<RecipeItem> getRecipeList() {
        return this.recipeList;
    }

    /**
     * Sets recipe list.
     *
     * @param recipeList the recipe list
     */
    public void setMovieList(List<RecipeItem> recipeList) {
        this.recipeList = recipeList;
    }


}
