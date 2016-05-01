package es.npatarino.android.gotchallenge.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ui.adapter.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Bind(R.id.container)
    ViewPager mViewPager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setSectionsPagerAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        getViewPager().setAdapter(getSectionsPagerAdapter());

        mTabLayout.setupWithViewPager(getViewPager());
    }

    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    public void setSectionsPagerAdapter(SectionsPagerAdapter aSectionsPagerAdapter) {
        this.mSectionsPagerAdapter = aSectionsPagerAdapter;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setViewPager(ViewPager aViewPager) {
        this.mViewPager = aViewPager;
    }


}
