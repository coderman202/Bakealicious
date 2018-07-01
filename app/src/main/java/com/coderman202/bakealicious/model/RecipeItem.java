package com.coderman202.bakealicious.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Recipe item.
 */
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

	/**
	 * Set image.
	 *
	 * @param image the image
	 */
	public void setImage(String image){
		this.image = image;
	}

	/**
	 * Get image string.
	 *
	 * @return the string
	 */
	public String getImage(){
		return image;
	}

	/**
	 * Set servings.
	 *
	 * @param servings the servings
	 */
	public void setServings(int servings){
		this.servings = servings;
	}

	/**
	 * Get servings int.
	 *
	 * @return the int
	 */
	public int getServings(){
		return servings;
	}

	/**
	 * Set name.
	 *
	 * @param name the name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Get name string.
	 *
	 * @return the string
	 */
	public String getName(){
		return name;
	}

	/**
	 * Set ingredients.
	 *
	 * @param ingredients the ingredients
	 */
	public void setIngredients(List<IngredientsItem> ingredients){
		this.ingredients = ingredients;
	}

	/**
	 * Get ingredients list.
	 *
	 * @return the list
	 */
	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}

	/**
	 * Get ingredients count int.
	 *
	 * @return the int
	 */
	public int getIngredientsCount(){
		return ingredients.size();
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
	public int getId(){
		return id;
	}

	/**
	 * Set steps.
	 *
	 * @param steps the steps
	 */
	public void setSteps(List<StepsItem> steps){
		this.steps = steps;
	}

	/**
	 * Get steps list.
	 *
	 * @return the list
	 */
	public List<StepsItem> getSteps(){
		return steps;
	}

	/**
	 * Get steps count int.
	 *
	 * @return the int
	 */
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

	/**
	 * Instantiates a new Recipe item.
	 */
	public RecipeItem() {
	}

	/**
	 * Instantiates a new Recipe item.
	 *
	 * @param in the in
	 */
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

	/**
	 * The constant CREATOR.
	 */
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