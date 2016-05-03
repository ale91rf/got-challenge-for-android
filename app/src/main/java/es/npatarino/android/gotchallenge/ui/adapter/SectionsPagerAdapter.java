package es.npatarino.android.gotchallenge.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.npatarino.android.gotchallenge.ui.fragment.GoTHousesListFragment;
import es.npatarino.android.gotchallenge.ui.fragment.GoTListFragment;

/**
 * Created by alejandro on 1/5/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager aFragmentManager) {
        super(aFragmentManager);
    }

    @Override
    public Fragment getItem(int aPosition) {
        if (aPosition == 0) {
            return new GoTListFragment();
        } else {
            return new GoTHousesListFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int aPosition) {
        switch (aPosition) {
            case 0:
                return "Characters";
            case 1:
                return "Houses";
        }
        return null;
    }
}