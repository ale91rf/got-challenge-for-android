package es.npatarino.android.gotchallenge.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.view.GotListView;

/**
 * Created by alejandro on 3/5/16.
 */
public class GoTListFragmentPresenterImp implements GoTListFragmentPresenter, DownloadDataCallback{

    private GotListView mView;
    private DownloadDataInteractor mDownloadInteractor;

    public GoTListFragmentPresenterImp(DownloadDataInteractor aInteractor, GotListView aView) {
        mView = aView;
        mDownloadInteractor = aInteractor;
    }

    @Override
    public void getDataFromApi() {
        mView.showProgressBar();
        mDownloadInteractor.downloadData(this);
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
