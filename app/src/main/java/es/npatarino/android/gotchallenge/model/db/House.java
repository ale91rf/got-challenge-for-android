package es.npatarino.android.gotchallenge.model.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alejandro on 5/5/16.
 */
public class House extends RealmObject{

    @PrimaryKey
    private String mHouseId;

    private String mHouseImageUrl;

    private String mHouseName;


    public String getmHouseId() {
        return mHouseId;
    }

    public void setmHouseId(String mHouseId) {
        this.mHouseId = mHouseId;
    }

    public String getmHouseImageUrl() {
        return mHouseImageUrl;
    }

    public void setmHouseImageUrl(String mHouseImageUrl) {
        this.mHouseImageUrl = mHouseImageUrl;
    }

    public String getmHouseName() {
        return mHouseName;
    }

    public void setmHouseName(String mHouseName) {
        this.mHouseName = mHouseName;
    }
}
