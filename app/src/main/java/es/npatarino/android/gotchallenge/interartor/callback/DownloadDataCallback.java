package es.npatarino.android.gotchallenge.interartor.callback;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by alejandro on 3/5/16.
 */
public interface DownloadDataCallback {

    public void dataDownloaded(List<GoTCharacter> aCharacters);

    public void dataNotDownloaded(String mText);
}
