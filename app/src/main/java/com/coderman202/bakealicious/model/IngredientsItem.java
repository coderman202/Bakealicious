package com.coderman202.bakealicious.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * The type Ingredients item.
 */
public class IngredientsItem implements Parcelable {

	@SerializedName("quantity")
	private double quantity;

	@SerializedName("measure")
	private String measure;

	@SerializedName("ingredient")
	private String ingredient;

	/**
	 * Set quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	/**
	 * Get quantity double.
	 *
	 * @return the double
	 */
	public double getQuantity(){
		return quantity;
	}

	/**
	 * Set measure.
	 *
	 * @param measure the measure
	 */
	public void setMeasure(String measure){
		this.measure = measure;
	}

	/**
	 * Get measure string.
	 *
	 * @return the string
	 */
	public String getMeasure(){
		return measure;
	}

	/**
	 * Set ingredient.
	 *
	 * @param ingredient the ingredient
	 */
	public void setIngredient(String ingredient){
		this.ingredient = ingredient;
	}

	/**
	 * Get ingredient string.
	 *
	 * @return the string
	 */
	public String getIngredient(){
		return ingredient;
	}

	@Override
 	public String toString(){
		return 
			"IngredientsItem{" + 
			"quantity = '" + quantity + '\'' + 
			",measure = '" + measure.toLowerCase() + '\'' +
			",ingredient = '" + ingredient + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.quantity);
		dest.writeString(this.measure);
		dest.writeString(this.ingredient);
	}

	/**
	 * Instantiates a new Ingredients item.
	 */
	public IngredientsItem() {
	}

	/**
	 * Instantiates a new Ingredients item.
	 *
	 * @param in the in
	 */
	protected IngredientsItem(Parcel in) {
		this.quantity = in.readDouble();
		this.measure = in.readString();
		this.ingredient = in.readString();
	}

	/**
	 * The constant CREATOR.
	 */
	public static final Parcelable.Creator<IngredientsItem> CREATOR = new Parcelable.Creator<IngredientsItem>() {
		@Override
		public IngredientsItem createFromParcel(Parcel source) {
			return new IngredientsItem(source);
		}

		@Override
		public IngredientsItem[] newArray(int size) {
			return new IngredientsItem[size];
		}
	};
}