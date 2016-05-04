package es.npatarino.android.gotchallenge.presenter;

import android.net.NetworkInfo;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.view.GotListView;
import es.npatarino.android.gotchallenge.util.Constants;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListFragmentPresenterImp implements GoTListFragmentPresenter, DownloadDataCallback{

    private GotListView mView;
    private DownloadDataInteractor mDownloadInteractor;
    private NetworkInfo mInfoRed;

    public GoTListFragmentPresenterImp(DownloadDataInteractor aInteractor, GotListView aView, NetworkInfo aInfoRed) {
        mView = aView;
        mDownloadInteractor = aInteractor;
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
    public void dataDownloaded(List<GoTCharacter> aCharacters) {
        mView.hideProgressBar();
        mView.displayList(aCharacters);
    }

    @Override
    public void dataNotDownloaded(String aText) {
        mView.hideProgressBar();
        mView.showMessage(aText);
    }

}
