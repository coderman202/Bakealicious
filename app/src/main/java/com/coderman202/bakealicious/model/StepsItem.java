package com.coderman202.bakealicious.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * The type Steps item.
 */
public class StepsItem implements Parcelable {

	@SerializedName("videoURL")
	private String videoURL;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("shortDescription")
	private String shortDescription;

	@SerializedName("thumbnailURL")
	private String thumbnailURL;

	/**
	 * Set video url.
	 *
	 * @param videoURL the video url
	 */
	public void setVideoURL(String videoURL){
		this.videoURL = videoURL;
	}

	/**
	 * Get video url string.
	 *
	 * @return the string
	 */
	public String getVideoURL(){
		return videoURL;
	}

	/**
	 * Set description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * Get description string.
	 *
	 * @return the string
	 */
	public String getDescription(){
		return description;
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
	 * Set short description.
	 *
	 * @param shortDescription the short description
	 */
	public void setShortDescription(String shortDescription){
		this.shortDescription = shortDescription;
	}

	/**
	 * Get short description string.
	 *
	 * @return the string
	 */
	public String getShortDescription(){
		return shortDescription;
	}

	/**
	 * Set thumbnail url.
	 *
	 * @param thumbnailURL the thumbnail url
	 */
	public void setThumbnailURL(String thumbnailURL){
		this.thumbnailURL = thumbnailURL;
	}

	/**
	 * Get thumbnail url string.
	 *
	 * @return the string
	 */
	public String getThumbnailURL(){
		return thumbnailURL;
	}

	@Override
 	public String toString(){
		return 
			"StepsItem{" + 
			"videoURL = '" + videoURL + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",shortDescription = '" + shortDescription + '\'' + 
			",thumbnailURL = '" + thumbnailURL + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.videoURL);
		dest.writeString(this.description);
		dest.writeInt(this.id);
		dest.writeString(this.shortDescription);
		dest.writeString(this.thumbnailURL);
	}

	/**
	 * Instantiates a new Steps item.
	 */
	public StepsItem() {
	}

	/**
	 * Instantiates a new Steps item.
	 *
	 * @param in the in
	 */
	protected StepsItem(Parcel in) {
		this.videoURL = in.readString();
		this.description = in.readString();
		this.id = in.readInt();
		this.shortDescription = in.readString();
		this.thumbnailURL = in.readString();
	}

	/**
	 * The constant CREATOR.
	 */
	public static final Parcelable.Creator<StepsItem> CREATOR = new Parcelable.Creator<StepsItem>() {
		@Override
		public StepsItem createFromParcel(Parcel source) {
			return new StepsItem(source);
		}

		@Override
		public StepsItem[] newArray(int size) {
			return new StepsItem[size];
		}
	};
}