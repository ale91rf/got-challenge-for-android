package es.npatarino.android.gotchallenge.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presenter.DetailActivityPresenterImp;
import es.npatarino.android.gotchallenge.ui.view.DetailView;
import es.npatarino.android.gotchallenge.util.Constants;

public class DetailActivity extends AppCompatActivity implements DetailView{


    private static final String TAG = "DetailActivity";

    @Bind(R.id.iv_photo)
    ImageView mImage;

    @Bind(R.id.tv_name)
    TextView mLblName;

    @Bind(R.id.tv_description)
    TextView mLblDescription;

    @Bind(R.id.t)
    Toolbar mToolbar;

    private DetailActivityPresenterImp mPresenter;
    private String mDescription;
    private String mName;
    private String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        injectDependencies();
        mPresenter.getDataFromIntent();
        setupToolbar();



    }

    @Override
    public void setupToolbar() {
        mToolbar.setTitle(mName);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void injectDependencies() {
        mPresenter = new DetailActivityPresenterImp(this);
    }

    @Override
    public void getData() {
        mDescription = getIntent().getStringExtra(Constants.DESCRIPTION);
        mName = getIntent().getStringExtra(Constants.NAME);
        mImageUrl = getIntent().getStringExtra(Constants.IMAGE_URL);

        mPresenter.showData();
    }

    @Override
    public void showData() {
        Picasso.with(getApplicationContext()).load(mImageUrl).into(mImage);
        mLblName.setText(mName);
        mLblDescription.setText(mDescription);

    }

    @Override
    public void doParallax() {

    }

    @Override
    public void showMessage(String aMessage) {
        Toast.makeText(getApplicationContext(), aMessage, Toast.LENGTH_LONG).show();
    }
}
