package es.npatarino.android.gotchallenge.db;



import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alejandro on 4/5/16.
 */
public class Character extends RealmObject {
    @PrimaryKey
    private String mName;

    private String mImageUrl;

    private String mDescription;

    private String mHouseImageUrl;

    private String mHouseName;

    private String mHouseId;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
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

    public String getmHouseId() {
        return mHouseId;
    }

    public void setmHouseId(String mHouseId) {
        this.mHouseId = mHouseId;
    }
}
