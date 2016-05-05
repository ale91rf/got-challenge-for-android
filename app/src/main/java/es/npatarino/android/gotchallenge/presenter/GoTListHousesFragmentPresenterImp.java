package es.npatarino.android.gotchallenge.presenter;

import android.net.NetworkInfo;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.GetDataHouseBDInteractor;
import es.npatarino.android.gotchallenge.interartor.SearchingHousesInteractor;
import es.npatarino.android.gotchallenge.interartor.SetDataHouseBDInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.interartor.callback.GetDataHousesCallback;
import es.npatarino.android.gotchallenge.interartor.callback.SearchingHousesCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.view.GoTHousesListView;
import es.npatarino.android.gotchallenge.util.Constants;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListHousesFragmentPresenterImp implements GoTListHousesFragmentPresenter,
        DownloadDataCallback, SearchingHousesCallback, GetDataHousesCallback {

    private GoTHousesListView mView;
    private DownloadDataInteractor mDownloadInteractor;
    private SearchingHousesInteractor mSearchingInteractor;
    private NetworkInfo mInfoRed;
    private SetDataHouseBDInteractor mSetDataDBInteractor;
    private GetDataHouseBDInteractor mGetDataBDInteractor;

    public GoTListHousesFragmentPresenterImp(DownloadDataInteractor aDownloadInteractor,
                                             SearchingHousesInteractor aSearchingInteractor, GoTHousesListView aView,
                                             NetworkInfo aInfoRed, SetDataHouseBDInteractor aSetDataDBInteractor,
                                             GetDataHouseBDInteractor aGetDataBDInteractor) {
        mView = aView;
        mDownloadInteractor = aDownloadInteractor;
        mSearchingInteractor = aSearchingInteractor;
        mInfoRed = aInfoRed;
        mSetDataDBInteractor = aSetDataDBInteractor;
        mGetDataBDInteractor = aGetDataBDInteractor;
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
        mGetDataBDInteractor.getHouses(this);
    }

    @Override
    public void dataFound(List<GoTHouse> aHouses) {
        mView.hideProgressBar();
        mView.displayList(aHouses);

        //store houses locally through Realm
        mSetDataDBInteractor.setData(aHouses);
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

    @Override
    public void housesFromDB(List<GoTHouse> aHouses) {
        mView.hideProgressBar();
        mView.displayList(aHouses);
    }
}
