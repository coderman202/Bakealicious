package com.coderman202.bakealicious.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderman202.bakealicious.R;
import com.coderman202.bakealicious.model.IngredientsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Reggie on 13/06/2018.
 * Custom adapter to handle the list of ingredients for a given recipe.
 */
public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder> {

    private static final String LOG_TAG = IngredientsListAdapter.class.getSimpleName();

    private Context context;
    private List<IngredientsItem> ingredientsItems;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ingredient_info) TextView ingredientInfoView;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public IngredientsListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientsListAdapter.ViewHolder holder, int position) {
        final IngredientsItem ingredientsItem = ingredientsItems.get(position);

        String quantity = String.valueOf(ingredientsItem.getQuantity());
        String measure = ingredientsItem.getMeasure();
        String ingredient = ingredientsItem.getIngredient();

        String ingredientInfo = context.getString(R.string.ingredient_details, quantity, measure, ingredient);

        holder.ingredientInfoView.setText(ingredientInfo);
    }

    @Override
    public int getItemCount() {
        if(ingredientsItems == null){
            return 0;
        }
        return ingredientsItems.size();
    }

    public void setIngredientList(List<IngredientsItem> ingredientsItems){
        this.ingredientsItems = ingredientsItems;
        notifyDataSetChanged();
    }
}
