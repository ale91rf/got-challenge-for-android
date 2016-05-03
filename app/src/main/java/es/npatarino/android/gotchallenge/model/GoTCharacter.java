package es.npatarino.android.gotchallenge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolás Patarino on 21/02/16.
 */
public class GoTCharacter {

    @SerializedName("name")
    private String mName;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("houseImageUrl")
    private String mHouseImageUrl;
    @SerializedName("houseName")
    private String mHouseName;
    @SerializedName("houseId")
    private String mHouseId;

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
