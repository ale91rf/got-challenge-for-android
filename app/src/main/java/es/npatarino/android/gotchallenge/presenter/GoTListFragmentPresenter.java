package es.npatarino.android.gotchallenge.presenter;

/**
 * Created by alejandro on 3/5/16.
 */
public interface GoTListFragmentPresenter {

    public void getDataFromApi();

    public void getDataFromDB();

    public void getCharactersByQuery(String aQuery);

}
