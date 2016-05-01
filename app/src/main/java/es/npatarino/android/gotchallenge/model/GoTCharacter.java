package es.npatarino.android.gotchallenge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolás Patarino on 21/02/16.
 */
public class GoTCharacter {

    @SerializedName("name")
    public String mName;
    @SerializedName("imageUrl")
    public String mImageUrl;
    @SerializedName("description")
    public String mDescription;
    @SerializedName("houseImageUrl")
    public String mHouseImageUrl;
    @SerializedName("houseName")
    public String mHouseName;
    @SerializedName("houseId")
    public String mHouseId;

    public String getHouseImageUrl() {
        return mHouseImageUrl;
    }

    public void setHouseImageUrl(String aHouseImageUrl) {
        this.mHouseImageUrl = aHouseImageUrl;
    }

    public String getHouseName() {
        return mHouseName;
    }

    public void setHouseName(String aHouseName) {
        this.mHouseName = aHouseName;
    }

    public String getHouseId() {
        return mHouseId;
    }

    public void setHouseId(String aHouseId) {
        this.mHouseId = aHouseId;
    }

    public String getName() {
        return mName;
    }

    public void setN(String aName) {
        this.mName = aName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String aImageUrl) {
        this.mImageUrl = aImageUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String aDescription) {
        this.mDescription = aDescription;
    }


}
