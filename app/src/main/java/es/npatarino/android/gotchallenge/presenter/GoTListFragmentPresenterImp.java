package es.npatarino.android.gotchallenge.presenter;

import android.net.NetworkInfo;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.GetDataCharacterBDInteractor;
import es.npatarino.android.gotchallenge.interartor.GetDataCharacterByQueryInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.interartor.SetDataCharacterBDInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.GetDataCharacterCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.view.GotListView;
import es.npatarino.android.gotchallenge.util.Constants;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListFragmentPresenterImp implements GoTListFragmentPresenter, DownloadDataCallback,
        GetDataCharacterCallback {

    private GotListView mView;
    private DownloadDataInteractor mDownloadInteractor;
    private NetworkInfo mInfoRed;
    private SetDataCharacterBDInteractor mSetDataDBInteractor;
    private GetDataCharacterBDInteractor mGetDataBaseInteractor;
    private GetDataCharacterByQueryInteractor mGetDataByQueryInteractor;

    public GoTListFragmentPresenterImp(DownloadDataInteractor aInteractor, GotListView aView,
                                       NetworkInfo aInfoRed, SetDataCharacterBDInteractor aSetDataInteractor,
                                       GetDataCharacterBDInteractor aDataBaseInteractor,
                                       GetDataCharacterByQueryInteractor aGetDataByQueryInteractor) {
        mView = aView;
        mDownloadInteractor = aInteractor;
        mInfoRed = aInfoRed;
        mSetDataDBInteractor = aSetDataInteractor;
        mGetDataBaseInteractor = aDataBaseInteractor;
        mGetDataByQueryInteractor = aGetDataByQueryInteractor;
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
        mGetDataBaseInteractor.getCharacters(this);
    }

    @Override
    public void getCharactersByQuery(String aQuery) {
        mGetDataByQueryInteractor.getCharacterByQuery(this, aQuery);
    }

    @Override
    public void dataDownloaded(List<GoTCharacter> aCharacters) {
        mView.hideProgressBar();
        mView.displayList(aCharacters);

        //store data locally as well.
        mSetDataDBInteractor.setData(aCharacters);
    }

    @Override
    public void dataNotDownloaded(String aText) {
        mView.hideProgressBar();
        mView.showMessage(aText);
    }

    @Override
    public void charactersFromDB(List<GoTCharacter> aCharacters) {
        mView.hideProgressBar();
        mView.displayList(aCharacters);
    }
}
