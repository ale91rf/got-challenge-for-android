package es.npatarino.android.gotchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.SearchingHousesInteractor;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.networking.GoTChallengeAPI;
import es.npatarino.android.gotchallenge.networking.NetworkRequest;
import es.npatarino.android.gotchallenge.networking.RestAPI;
import es.npatarino.android.gotchallenge.presenter.GoTListHousesFragmentPresenterImp;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.ui.view.GoTHousesListView;
import rx.Subscription;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTHousesListFragment extends Fragment implements GoTHousesListView{

    private static final String TAG = "GoTHousesListFragment";


    @Bind(R.id.pb)
    ContentLoadingProgressBar mProgress;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    private GoTHouseAdapter mGoTHouseAdapter;
    private GoTListHousesFragmentPresenterImp mPresenter;

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, mRootView);
        injectDependencies();
        setupRecyclerView();
        getData();

        return mRootView;
    }

    @Override
    public void displayList(List<GoTHouse> aList) {
        mGoTHouseAdapter.addAll(aList);
        mGoTHouseAdapter.notifyDataSetChanged();
    }

    @Override
    public void injectDependencies() {
        RestAPI lApi = GoTChallengeAPI.getApiInterface(getActivity());
        DownloadDataInteractor lDownloadInteractor = new DownloadDataInteractor(lApi);

        mPresenter = new GoTListHousesFragmentPresenterImp(lDownloadInteractor, new SearchingHousesInteractor(), this);

    }

    @Override
    public void showProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgress.hide();
    }

    @Override
    public void setupRecyclerView() {
        mGoTHouseAdapter = new GoTHouseAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTHouseAdapter);
    }

    @Override
    public void showMessage(String aMessage) {
        Toast.makeText(getActivity(), aMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData() {
        mPresenter.getDataFromApi();
    }
}