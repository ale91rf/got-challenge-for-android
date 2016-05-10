package es.npatarino.android.gotchallenge.ui.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.interartor.DownloadDataInteractor;
import es.npatarino.android.gotchallenge.interartor.GetDataHouseBDInteractor;
import es.npatarino.android.gotchallenge.interartor.SearchingHousesInteractor;
import es.npatarino.android.gotchallenge.interartor.SetDataHouseBDInteractor;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.networking.GoTChallengeApi;
import es.npatarino.android.gotchallenge.networking.RestApi;
import es.npatarino.android.gotchallenge.presenter.GoTListHousesFragmentPresenterImp;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.ui.view.GoTHousesListView;
import io.realm.Realm;

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

    /*
    * DI - Dependency inversion
    * dependency injection manually
    * In the future we could do it with Dagger2
    */
    @Override
    public void injectDependencies() {
        RestApi lApi = GoTChallengeApi.getApiInterface(getActivity());
        DownloadDataInteractor lDownloadInteractor = new DownloadDataInteractor(lApi);
        ConnectivityManager lConectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo lNetInfo = lConectivityManager.getActiveNetworkInfo();
        Realm lRealm = Realm.getDefaultInstance();
        SetDataHouseBDInteractor lSetDataHouseBDInteractor = new SetDataHouseBDInteractor(lRealm);
        GetDataHouseBDInteractor lGetDataHouseBDInteractor = new GetDataHouseBDInteractor(lRealm);
        mPresenter = new GoTListHousesFragmentPresenterImp(lDownloadInteractor, new SearchingHousesInteractor(),
                this, lNetInfo, lSetDataHouseBDInteractor, lGetDataHouseBDInteractor);

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