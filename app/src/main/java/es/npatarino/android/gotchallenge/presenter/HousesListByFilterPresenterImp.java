package es.npatarino.android.gotchallenge.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.GetCharacterByFilterInteractor;
import es.npatarino.android.gotchallenge.interartor.GetDataCharacterBDInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.GetDataCharacterCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.view.GotListView;

/**
 * Created by alejandro on 5/5/16.
 */
public class HousesListByFilterPresenterImp implements HousesListByFilterPresenter, GetDataCharacterCallback{

    private GotListView mView;
    private GetCharacterByFilterInteractor mInteractor;

    public HousesListByFilterPresenterImp(GotListView aView, GetCharacterByFilterInteractor aInteractor){
        mView = aView;
        mInteractor = aInteractor;
    }

    @Override
    public void getDataFromDB(String aId) {
        mView.showProgressBar();
        mInteractor.getCharactersByFilter(this, aId);
    }

    @Override
    public void charactersFromDB(List<GoTCharacter> aCharacters) {
        mView.hideProgressBar();
        mView.displayList(aCharacters);
    }
}
