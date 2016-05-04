package es.npatarino.android.gotchallenge.presenter;

import es.npatarino.android.gotchallenge.ui.view.DetailView;

/**
 * Created by alejandro on 4/5/16.
 */
public class DetailActivityPresenterImp implements DetailActivityPresenter {

    private DetailView mView;

    public DetailActivityPresenterImp(DetailView aView) {
        mView = aView;
    }

    @Override
    public void getDataFromIntent() {
        mView.getData();
    }

    @Override
    public void parallax() {
        mView.doParallax();
    }

    @Override
    public void showData() {
        mView.showData();
    }
}
