package es.npatarino.android.gotchallenge.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.SearchingHousesInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.interartor.callback.SearchingHousesCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.view.GoTHousesListView;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListHousesFragmentPresenterImp implements GoTListHousesFragmentPresenter,
        DownloadDataCallback, SearchingHousesCallback {

    private GoTHousesListView mView;
    private DownloadDataInteractor mDownloadInteractor;
    private SearchingHousesInteractor mSearchingInteractor;

    public GoTListHousesFragmentPresenterImp(DownloadDataInteractor aDownloadInteractor,
                                             SearchingHousesInteractor aSearchingInteractor, GoTHousesListView aView) {
        mView = aView;
        mDownloadInteractor = aDownloadInteractor;
        mSearchingInteractor = aSearchingInteractor;
    }

    @Override
    public void getDataFromApi() {
        mView.showProgressBar();
        mDownloadInteractor.downloadData(this);
    }

    @Override
    public void dataFound(List<GoTHouse> aHouses) {
        mView.hideProgressBar();
        mView.displayList(aHouses);
    }

    @Override
    public void dataNotFound(String aText) {
        mView.hideProgressBar();
        mView.showMessage(aText);
    }

    @Override
    public void dataDownloaded(List<GoTCharacter> aCharacters) {
        mSearchingInteractor.searchingHouses(this, aCharacters);
    }

    @Override
    public void dataNotDownloaded(String aText) {
        mView.hideProgressBar();
        mView.showMessage(aText);
    }
}
