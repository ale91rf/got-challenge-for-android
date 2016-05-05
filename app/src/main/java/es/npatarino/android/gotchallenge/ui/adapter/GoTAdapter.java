package es.npatarino.android.gotchallenge.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.activity.DetailActivity;
import es.npatarino.android.gotchallenge.util.Constants;

/**
 * Created by alejandro on 1/5/16.
 */
public class GoTAdapter extends RecyclerView.Adapter<GoTAdapter.GotCharacterViewHolder> {

    private List<GoTCharacter> mCharacterList;
    private Activity mActivity;

    public GoTAdapter(Activity aActivity) {
        this.mCharacterList = new ArrayList<>();
        mActivity = aActivity;
    }

    public void addAll(Collection<GoTCharacter> collection) {
        mCharacterList.clear();
        for (int i = 0; i < collection.size(); i++) {
            mCharacterList.add((GoTCharacter) collection.toArray()[i]);
        }
    }

    @Override
    public GotCharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(GotCharacterViewHolder aHolder, int aPosition) {

        aHolder.render(mCharacterList.get(aPosition), mActivity);
        aHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(mActivity, DetailActivity.class);
                lIntent.putExtra(Constants.DESCRIPTION, mCharacterList.get(aPosition).getDescription());
                lIntent.putExtra(Constants.NAME, mCharacterList.get(aPosition).getName());
                lIntent.putExtra(Constants.IMAGE_URL, mCharacterList.get(aPosition).getImageUrl());
                mActivity.startActivity(lIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }


    public class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";


        @Nullable @Bind(R.id.ivBackground)
        ImageView mImageView;

        @Nullable @Bind(R.id.tv_name)
        TextView mLblName;

        public GotCharacterViewHolder(View aView) {
            super(aView);
            ButterKnife.bind(this,aView);

        }

        public void render(GoTCharacter aGoTCharacter, Activity aActivity) {

            Picasso.with(aActivity).load(aGoTCharacter.getImageUrl()).into(mImageView);
            mLblName.setText(aGoTCharacter.getName());

        }
    }

}