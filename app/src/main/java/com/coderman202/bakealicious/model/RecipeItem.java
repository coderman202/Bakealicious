package com.coderman202.bakealicious.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

// TODO (1) Add javadocs for all elements

public class RecipeItem implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("servings")
	private int servings;

	@SerializedName("name")
	private String name;

	@SerializedName("ingredients")
	private List<IngredientsItem> ingredients;

	@SerializedName("id")
	private int id;

	@SerializedName("steps")
	private List<StepsItem> steps;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setServings(int servings){
		this.servings = servings;
	}

	public int getServings(){
		return servings;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIngredients(List<IngredientsItem> ingredients){
		this.ingredients = ingredients;
	}

	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}

	public int getIngredientsCount(){
		return ingredients.size();
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSteps(List<StepsItem> steps){
		this.steps = steps;
	}

	public List<StepsItem> getSteps(){
		return steps;
	}

	public int getStepsCount(){
		return steps.size();
	}

	@Override
 	public String toString(){
		return 
			"RecipeItem{" +
			"image = '" + image + '\'' + 
			",servings = '" + servings + '\'' + 
			",name = '" + name + '\'' + 
			",ingredients = '" + ingredients + '\'' + 
			",id = '" + id + '\'' + 
			",steps = '" + steps + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeInt(this.servings);
		dest.writeString(this.name);
		dest.writeList(this.ingredients);
		dest.writeInt(this.id);
		dest.writeList(this.steps);
	}

	public RecipeItem() {
	}

	protected RecipeItem(Parcel in) {
		this.image = in.readString();
		this.servings = in.readInt();
		this.name = in.readString();
		this.ingredients = new ArrayList<IngredientsItem>();
		in.readList(this.ingredients, IngredientsItem.class.getClassLoader());
		this.id = in.readInt();
		this.steps = new ArrayList<StepsItem>();
		in.readList(this.steps, StepsItem.class.getClassLoader());
	}

	public static final Parcelable.Creator<RecipeItem> CREATOR = new Parcelable.Creator<RecipeItem>() {
		@Override
		public RecipeItem createFromParcel(Parcel source) {
			return new RecipeItem(source);
		}

		@Override
		public RecipeItem[] newArray(int size) {
			return new RecipeItem[size];
		}
	};
}