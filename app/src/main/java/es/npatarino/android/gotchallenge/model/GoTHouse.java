package es.npatarino.android.gotchallenge.model;

/**
 * Created by alejandro on 1/5/16.
 */

import com.google.gson.annotations.SerializedName;

public class GoTHouse {

    @SerializedName("houseImageUrl")
    private String mHouseImageUrl;
    @SerializedName("houseName")
    private String mHouseName;
    @SerializedName("houseId")
    private String mHouseId;

    public GoTHouse(){}

    public GoTHouse(String aHouseImageUrl, String aHouseName, String aHouseId) {
        this.mHouseImageUrl = aHouseImageUrl;
        this.mHouseName = aHouseName;
        this.mHouseId = aHouseId;
    }

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