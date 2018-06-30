package com.coderman202.bakealicious.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coderman202.bakealicious.fragments.StepFragment;
import com.coderman202.bakealicious.model.RecipeItem;
import com.coderman202.bakealicious.model.StepsItem;

import java.util.List;

public class StepPagerAdapter extends FragmentPagerAdapter {

    private static final String STEP_ITEM = "step_item";

    private RecipeItem recipeItem;

    public StepPagerAdapter(FragmentManager fragmentManager, RecipeItem recipeItem){
        super(fragmentManager);
        this.recipeItem = recipeItem;
    }
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position the position of the fragment item
     */
    @Override
    public Fragment getItem(int position) {
        if(position < recipeItem.getStepsCount()){
            List<StepsItem> stepsItemList = recipeItem.getSteps();
            StepsItem stepsItem = stepsItemList.get(position);
            StepFragment stepFragment = new StepFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(STEP_ITEM, stepsItem);
            stepFragment.setArguments(bundle);
            stepFragment.setStepItem(stepsItem);
            return stepFragment;
        }
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return recipeItem.getStepsCount();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "Step " + position;
    }
}
