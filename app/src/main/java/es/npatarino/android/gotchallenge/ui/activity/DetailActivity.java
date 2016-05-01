package es.npatarino.android.gotchallenge.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.util.Constants;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = "DetailActivity";

    @Bind(R.id.iv_photo)
    ImageView mImage;

    @Bind(R.id.tv_name)
    TextView mLblName;

    @Bind(R.id.tv_description)
    TextView mLblDescription;

    @Bind(R.id.t)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);


        final String mDescription = getIntent().getStringExtra(Constants.DESCRIPTION);
        final String mName = getIntent().getStringExtra(Constants.NAME);
        final String mImageUrl = getIntent().getStringExtra(Constants.IMAGE_URL);

        mToolbar.setTitle(mName);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(mImageUrl);
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    DetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImage.setImageBitmap(bmp);
                            mLblName.setText(mName);
                            mLblDescription.setText(mDescription);
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
    }
}
