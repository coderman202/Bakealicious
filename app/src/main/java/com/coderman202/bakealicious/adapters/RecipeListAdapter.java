package com.coderman202.bakealicious.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderman202.bakealicious.R;
import com.coderman202.bakealicious.RecipeDetailsActivity;
import com.coderman202.bakealicious.model.RecipeItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Reggie on 13/06/2018.
 * Custom adapter to handle the list of recipes on the main screen.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private static final String LOG_TAG = RecipeListAdapter.class.getSimpleName();

    private static final String RECIPE_ITEM_KEY = "Recipe";

    private Context context;
    private List<RecipeItem> recipeList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recipe_image) ImageView recipeImageView;
        @BindView(R.id.recipe_name) TextView recipeNameView;
        @BindView(R.id.recipe_ingredients) TextView numIngredientsView;
        @BindView(R.id.recipe_steps) TextView numStepsView;
        @BindView(R.id.recipe_servings) TextView numServingsView;
        @BindView(R.id.recipe_card) CardView recipeCardView;
        @BindView(R.id.view_recipe_button) TextView viewRecipeButton;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public RecipeListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        final RecipeItem recipeItem = recipeList.get(position);

        String recipeName = recipeItem.getName();
        String numIngredients = Integer.toString(recipeItem.getIngredientsCount());
        String numSteps = Integer.toString(recipeItem.getStepsCount());
        String numServings = Integer.toString(recipeItem.getServings());
        String recipeImageUrl = recipeItem.getImage();

        holder.recipeNameView.setText(recipeName);
        holder.numIngredientsView.setText(context.getString(R.string.num_ingredients, numIngredients));
        holder.numStepsView.setText(context.getString(R.string.num_steps, numSteps));
        holder.numServingsView.setText(context.getString(R.string.num_servings, numServings));

        if(recipeImageUrl.equals("")) recipeImageUrl = "http://www.wholebodyreboot.com/blog/wp-content/themes/WBR/images/recipe-placeholder.jpg";

        Picasso.with(context)
                .load(recipeImageUrl)
                .placeholder(R.drawable.recipe_dish_placeholder)
                .error(R.drawable.loading_error)
                .into(holder.recipeImageView);

        holder.recipeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_TAG, "Test");
            }
        });

        holder.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra(RECIPE_ITEM_KEY, recipeItem);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(recipeList == null){
            return 0;
        }
        return recipeList.size();
    }

    public void setRecipeList(List<RecipeItem> recipeList){
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }
}
