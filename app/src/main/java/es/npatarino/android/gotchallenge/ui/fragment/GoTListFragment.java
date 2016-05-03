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


import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.networking.GoTChallengeAPI;
import es.npatarino.android.gotchallenge.networking.NetworkRequest;
import es.npatarino.android.gotchallenge.networking.RestAPI;
import es.npatarino.android.gotchallenge.ui.adapter.GoTAdapter;
import rx.Subscription;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTListFragment extends Fragment {

    private static final String TAG = "GoTListFragment";

    @Bind(R.id.pb)
    ContentLoadingProgressBar mProgress;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    private GoTAdapter mGoTAdapter;

    public GoTListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, mRootView);


        mGoTAdapter = new GoTAdapter(getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTAdapter);

        RestAPI mApi = GoTChallengeAPI.getApiInterface(getActivity());

        Subscription mGetCharactersSubscription = NetworkRequest.performAsyncRequest(mApi.getCharacters(), (data) -> {
            mGoTAdapter.addAll(data);
            mGoTAdapter.notifyDataSetChanged();
            mProgress.hide();

        }, (error) -> {
            Log.e(TAG, error.getLocalizedMessage());
        });

        return mRootView;
    }
}