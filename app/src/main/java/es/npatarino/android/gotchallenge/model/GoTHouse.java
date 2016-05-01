package es.npatarino.android.gotchallenge.model;

/**
 * Created by alejandro on 1/5/16.
 */

import com.google.gson.annotations.SerializedName;

public class GoTHouse {

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

}