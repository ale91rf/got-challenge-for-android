package es.npatarino.android.gotchallenge.interartor.callback;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 3/5/16.
 */
public interface SearchingHousesCallback {

    public void dataFound(List<GoTHouse> aHouses);

    public void dataNotFound(String aText);
}
