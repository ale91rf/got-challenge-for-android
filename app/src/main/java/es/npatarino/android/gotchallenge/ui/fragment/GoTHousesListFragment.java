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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTHousesListFragment extends Fragment {

    private static final String TAG = "GoTHousesListFragment";

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

        mGoTHouseAdapter = new GoTHouseAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTHouseAdapter);

        new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://52.18.228.107:3000/characters";

                URL obj = null;
                try {
                    obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Type listType = new TypeToken<ArrayList<GoTCharacter>>() {
                    }.getType();
                    final List<GoTCharacter> characters = new Gson().fromJson(response.toString(), listType);
                    GoTHousesListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<GoTHouse> hs = new ArrayList<GoTHouse>();
                            for (int i = 0; i < characters.size(); i++) {
                                boolean b = false;
                                for (int j = 0; j < hs.size(); j++) {
                                    if (hs.get(j).mHouseName.equalsIgnoreCase(characters.get(i).mHouseName)) {
                                        b = true;
                                    }
                                }
                                if (!b) {
                                    if (characters.get(i).mHouseId != null && !characters.get(i).mHouseId.isEmpty()) {
                                        GoTHouse h = new GoTHouse();
                                        h.mHouseId = characters.get(i).mHouseId;
                                        h.mHouseName = characters.get(i).mHouseName;
                                        h.mHouseImageUrl = characters.get(i).mHouseImageUrl;
                                        hs.add(h);
                                        b = false;
                                    }
                                }
                            }
                            mGoTHouseAdapter.addAll(hs);
                            mGoTHouseAdapter.notifyDataSetChanged();
                            mProgress.hide();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
        return mRootView;
    }
}