package es.npatarino.android.gotchallenge.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.interartor.GetCharacterByFilterInteractor;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.presenter.HousesListByFilterPresenterImp;
import es.npatarino.android.gotchallenge.ui.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.ui.view.GenericActivity;
import es.npatarino.android.gotchallenge.ui.view.GotListView;
import es.npatarino.android.gotchallenge.util.Constants;
import io.realm.Realm;

/**
 * Created by alejandro on 5/5/16.
 */
public class HousesListByFilterActivity extends AppCompatActivity implements GotListView, GenericActivity{


    @Bind(R.id.pbLoading)
    ContentLoadingProgressBar mProgress;

    @Bind(R.id.rvCharactersList)
    RecyclerView mRecyclerView;

    @Bind(R.id.tHouseName)
    Toolbar mToolbar;


    private GoTAdapter mGoTAdapter;
    private HousesListByFilterPresenterImp mPresenter;

    private String mHouseId;
    private String mName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_by_filter);

        mHouseId = getIntent().getExtras().getString(Constants.HOUSE_ID);
        mName = getIntent().getExtras().getString(Constants.NAME);

        ButterKnife.bind(this);
        injectDependencies();
        setupRecyclerView();
        getData();
        setupToolbar();


    }

    @Override
    public void injectDependencies() {

        GetCharacterByFilterInteractor lInteractor = new GetCharacterByFilterInteractor(Realm.getDefaultInstance());
        mPresenter = new HousesListByFilterPresenterImp(this, lInteractor);

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
        mGoTAdapter = new GoTAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGoTAdapter);
    }

    @Override
    public void showMessage(String aMessage) {
        Toast.makeText(getApplicationContext(), aMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData() {
        mPresenter.getDataFromDB(mHouseId);
    }

    @Override
    public void displayList(List<GoTCharacter> aList) {
        mGoTAdapter.addAll(aList);
        mGoTAdapter.notifyDataSetChanged();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
