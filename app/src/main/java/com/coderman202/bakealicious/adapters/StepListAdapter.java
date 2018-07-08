package com.coderman202.bakealicious.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderman202.bakealicious.R;
import com.coderman202.bakealicious.StepsActivity;
import com.coderman202.bakealicious.fragments.StepFragment;
import com.coderman202.bakealicious.model.RecipeItem;
import com.coderman202.bakealicious.model.StepsItem;

import java.util.List;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Reggie on 13/06/2018.
 * Custom adapter to handle the list of steps for a given recipe.
 */
public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ViewHolder> {

    private static final String LOG_TAG = StepListAdapter.class.getSimpleName();

    private static final String RECIPE_ITEM_KEY = "recipe_item";
    private static final String STEP_ITEM_KEY = "step_item";
    private static final String STEP_POSITION_KEY = "position_key";

    private Context context;
    private List<StepsItem> stepsItems;
    private RecipeItem recipeItem;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.step_short_desc) TextView stepShortDescView;
        @BindView(R.id.step_list_item) ConstraintLayout stepItemView;

        // If user is on a tablet, use master-detail flow.
        @BindBool(R.bool.isTablet) boolean isTabletMode;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public StepListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public StepListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StepListAdapter.ViewHolder holder, final int position) {
        stepsItems = recipeItem.getSteps();
        final StepsItem stepsItem = stepsItems.get(position);

        final int pos = holder.getAdapterPosition();

        final boolean isTabletMode = holder.isTabletMode;

        String stepNum = String.valueOf(stepsItem.getId());
        String shortDesc = stepsItem.getShortDescription();

        String ingredientInfo = context.getString(R.string.step_short_desc, stepNum, shortDesc);

        holder.stepShortDescView.setText(ingredientInfo);

        holder.stepItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isTabletMode){
                    StepFragment stepFragment = new StepFragment();
                    Bundle args = new Bundle();
                    args.putParcelable(STEP_ITEM_KEY, stepsItem);
                    stepFragment.setArguments(args);
                    FragmentManager stepFragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    stepFragmentManager.beginTransaction()
                            .replace(R.id.step_item_detail_container, stepFragment)
                            .commit();
                }
                else{
                    Intent intent = new Intent(context, StepsActivity.class);
                    intent.putExtra(RECIPE_ITEM_KEY, recipeItem);
                    intent.putExtra(STEP_POSITION_KEY, pos);
                    ((Activity)context).startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(stepsItems == null){
            return 0;
        }
        return stepsItems.size();
    }

    public void setRecipeItem(RecipeItem recipeItem){
        this.recipeItem = recipeItem;
        this.stepsItems = recipeItem.getSteps();
        notifyDataSetChanged();
    }
}
