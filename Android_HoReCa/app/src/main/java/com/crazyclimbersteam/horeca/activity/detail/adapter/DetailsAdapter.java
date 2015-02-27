package com.crazyclimbersteam.horeca.activity.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.DetailsFeedbackFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.DetailsInfoFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.photo.DetailsPhotoFragment;

import java.util.List;

/**
 * @author Mirash
 */
public class DetailsAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;

    public DetailsAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return DetailsActivity.DETAIL_SCREENS_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DetailsInfoFragment();
            case 1:
                return new DetailsFeedbackFragment();
            case 2:
                return new DetailsPhotoFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}
