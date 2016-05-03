package es.npatarino.android.gotchallenge.interartor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.npatarino.android.gotchallenge.interartor.callback.SearchingHousesCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 3/5/16.
 */
public class SearchingHousesInteractor  {

    private List<GoTCharacter> mCharacters;
    private Set mSet;


    public SearchingHousesInteractor() {

        mSet = new HashSet();
    }

    public void searchingHouses(final SearchingHousesCallback aCallback, List<GoTCharacter> aCharacters){

        mCharacters = aCharacters;

        ArrayList<GoTHouse> lHouses = new ArrayList<GoTHouse>();

        for (GoTCharacter lCharacter : mCharacters) {
            if(isNotDuplicated(lCharacter.getHouseName())){
                GoTHouse lHouse = new GoTHouse(lCharacter.getHouseImageUrl(), lCharacter.getHouseName(),
                        lCharacter.getHouseId());
                lHouses.add(lHouse);
            }
        }

        if (thereAreData(lHouses.size())) {
            aCallback.dataFound(lHouses);
        }else {
            aCallback.dataNotFound("No data to show");
        }

    }

    private boolean thereAreData(int aSize) {
        return aSize > 0;
    }

    private boolean isNotDuplicated(String mHouseName) {

        if (isNameEmpty(mHouseName)) return false;

        //false = duplicated
        return mSet.add(mHouseName);
    }

    private boolean isNameEmpty(String mHouseName) {
        return mHouseName == "";
    }
}
