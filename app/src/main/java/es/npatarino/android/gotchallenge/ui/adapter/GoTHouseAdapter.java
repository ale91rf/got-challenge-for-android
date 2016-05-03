package es.npatarino.android.gotchallenge.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<GoTHouseAdapter.GotHouseViewHolder> {

    private List<GoTHouse> mHousesList;
    private Activity mActivity;

    public GoTHouseAdapter(Activity aActivity) {
        this.mHousesList = new ArrayList<>();
        mActivity = aActivity;
    }

    public void addAll(Collection<GoTHouse> collection) {
        for (int i = 0; i < collection.size(); i++) {
            mHousesList.add((GoTHouse) collection.toArray()[i]);
        }
    }

    @Override
    public GotHouseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotHouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(GotHouseViewHolder aHolder, int aPosition) {
        aHolder.render(mHousesList.get(aPosition), mActivity);
    }

    @Override
    public int getItemCount() {
        return mHousesList.size();
    }



    class GotHouseViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";

        @Nullable @Bind(R.id.ivBackground)
        ImageView mImageView;

        public GotHouseViewHolder(View aView) {
            super(aView);
            ButterKnife.bind(this, aView);
        }

        public void render(GoTHouse aGoTHouse, Activity aActivity) {
            Picasso.with(aActivity).load(aGoTHouse.getHouseImageUrl()).into(mImageView);
        }
    }

}