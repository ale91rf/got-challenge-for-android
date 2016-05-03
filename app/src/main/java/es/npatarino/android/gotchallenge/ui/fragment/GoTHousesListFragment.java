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
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.networking.GoTChallengeAPI;
import es.npatarino.android.gotchallenge.networking.NetworkRequest;
import es.npatarino.android.gotchallenge.networking.RestAPI;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;
import rx.Subscription;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTHousesListFragment extends Fragment {

    private static final String TAG = "GoTHousesListFragment";

    private Set mSet;

    @Bind(R.id.pb)
    ContentLoadingProgressBar mProgress;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    private GoTHouseAdapter mGoTHouseAdapter;

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, mRootView);

        mSet = new HashSet();

        mGoTHouseAdapter = new GoTHouseAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTHouseAdapter);

        RestAPI mApi = GoTChallengeAPI.getApiInterface(getActivity());

        Subscription mGetCharactersSubscription = NetworkRequest.performAsyncRequest(mApi.getCharacters(), (data) -> {
            List<GoTCharacter> mCharacters = data;

            ArrayList<GoTHouse> mHouses = new ArrayList<GoTHouse>();

            for (GoTCharacter lCharacter : mCharacters) {
                if(isNotDuplicated(lCharacter.getHouseName())){
                    GoTHouse lHouse = new GoTHouse(lCharacter.getHouseImageUrl(), lCharacter.getHouseName(),
                            lCharacter.getHouseId());
                    mHouses.add(lHouse);
                }
            }

            mGoTHouseAdapter.addAll(mHouses);
            mGoTHouseAdapter.notifyDataSetChanged();
            mProgress.hide();

        }, (error) -> {
            Log.e(TAG, error.getLocalizedMessage());
        });

        return mRootView;
    }

    private boolean isNotDuplicated(String mHouseName) {

        if (isNameEmpty(mHouseName)) return false;

        //false = duplicated
        return mSet.add(mHouseName);
    }

    private boolean isNameEmpty(String mHouseName) {
        return mHouseName == "";
    }
}