package es.npatarino.android.gotchallenge.interartor.callback;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by alejandro on 4/5/16.
 */
public interface GetDataCallback {

    public void dataFromDB(List<GoTCharacter> aCharacters);
}
