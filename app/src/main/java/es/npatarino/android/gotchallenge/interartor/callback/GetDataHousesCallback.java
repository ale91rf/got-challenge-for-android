package es.npatarino.android.gotchallenge.interartor.callback;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 5/5/16.
 */
public interface GetDataHousesCallback {

    public void housesFromDB(List<GoTHouse> aHouses);
}
