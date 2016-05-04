package es.npatarino.android.gotchallenge.presenter;

import android.net.NetworkInfo;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.SearchingHousesInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.interartor.callback.SearchingHousesCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.view.GoTHousesListView;
import es.npatarino.android.gotchallenge.util.Constants;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListHousesFragmentPresenterImp implements GoTListHousesFragmentPresenter,
        DownloadDataCallback, SearchingHousesCallback {

    private GoTHousesListView mView;
    private DownloadDataInteractor mDownloadInteractor;
    private SearchingHousesInteractor mSearchingInteractor;
    private NetworkInfo mInfoRed;

    public GoTListHousesFragmentPresenterImp(DownloadDataInteractor aDownloadInteractor,
                                             SearchingHousesInteractor aSearchingInteractor, GoTHousesListView aView,
                                             NetworkInfo aInfoRed) {
        mView = aView;
        mDownloadInteractor = aDownloadInteractor;
        mSearchingInteractor = aSearchingInteractor;
        mInfoRed = aInfoRed;
    }

    @Override
    public void getDataFromApi() {
        if (Constants.isConnectionAvailable(mInfoRed)){
            mView.showProgressBar();
            mDownloadInteractor.downloadData(this);
        }else {
            getDataFromDB();
        }
    }

    @Override
    public void getDataFromDB() {

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
