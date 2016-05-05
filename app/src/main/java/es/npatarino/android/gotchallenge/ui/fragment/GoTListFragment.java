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
import es.npatarino.android.gotchallenge.interartor.GetDataCharacterBDInteractor;
import es.npatarino.android.gotchallenge.interartor.SetDataCharacterBDInteractor;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.networking.GoTChallengeAPI;
import es.npatarino.android.gotchallenge.networking.RestAPI;
import es.npatarino.android.gotchallenge.presenter.GoTListFragmentPresenterImp;
import es.npatarino.android.gotchallenge.ui.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.ui.view.GotListView;
import io.realm.Realm;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTListFragment extends Fragment implements GotListView {

    private static final String TAG = "GoTListFragment";

    @Bind(R.id.pb)
    ContentLoadingProgressBar mProgress;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    private GoTAdapter mGoTAdapter;
    private GoTListFragmentPresenterImp mPresenter;

    public GoTListFragment() {
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

    /*
    * D - Dependency inversion
    * In the future we could do it with Dagger2
    * TODO
    */
    @Override
    public void injectDependencies() {
        RestAPI lApi = GoTChallengeAPI.getApiInterface(getActivity());
        DownloadDataInteractor lInteractor = new DownloadDataInteractor(lApi);
        ConnectivityManager lConectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo lNetInfo = lConectivityManager.getActiveNetworkInfo();
        SetDataCharacterBDInteractor lSetDataInteractor = new SetDataCharacterBDInteractor(Realm.getDefaultInstance());
        GetDataCharacterBDInteractor lGetDataInteractor = new GetDataCharacterBDInteractor(Realm.getDefaultInstance());
        mPresenter = new GoTListFragmentPresenterImp(lInteractor, this, lNetInfo, lSetDataInteractor, lGetDataInteractor);
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
    public void getData() {
        mPresenter.getDataFromApi();
    }

    @Override
    public void displayList(List<GoTCharacter> aList) {
        mGoTAdapter.addAll(aList);
        mGoTAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String aMessage) {
        Toast.makeText(getActivity(), aMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupRecyclerView() {
        mGoTAdapter = new GoTAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTAdapter);
    }
}