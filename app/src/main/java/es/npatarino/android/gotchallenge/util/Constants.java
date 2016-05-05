package es.npatarino.android.gotchallenge.util;

import android.net.NetworkInfo;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.model.db.Character;
import es.npatarino.android.gotchallenge.model.db.House;

/**
 * Created by alejandro on 2/5/16.
 */
public class Constants {

    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";

    public static final String HOUSE_ID = "houseId";

    public static final String DB_NAME = "GoTDataBase";

    public static boolean isConnectionAvailable(NetworkInfo aInfoRed) {
        // return if there is internet connection
        return (aInfoRed != null && aInfoRed.isConnected());
    }

}
