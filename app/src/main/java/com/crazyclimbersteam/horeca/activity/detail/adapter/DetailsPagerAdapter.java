package com.crazyclimbersteam.horeca.activity.detail.adapter;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;

import com.alexmirash.parallaxheaderviewpager.TabPagerAdapter;
import com.alexmirash.parallaxheaderviewpager.fragment.ScrollTabHolderFragment;
import com.crazyclimbersteam.horeca.HorecApplication;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.DetailsFeedbackFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.DetailInfoFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.photo.DetailsPhotoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class DetailsPagerAdapter extends TabPagerAdapter {
    public DetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    protected List<String> getTitles() {
        Resources res = HorecApplication.getInstance().getResources();
        List<String> titles = new ArrayList<>(3);
        titles.add(res.getString(R.string.details_info_tab_title));
        titles.add(res.getString(R.string.details_feedback_tab_title));
        titles.add(res.getString(R.string.details_photos_tab_title));
        return titles;
    }

    @Override
    protected ScrollTabHolderFragment getTabItem(int position) {
        switch (position) {
            case 0:
                return new DetailInfoFragment();
            case 1:
                return new DetailsFeedbackFragment();
            case 2:
                return new DetailsPhotoFragment();
            default:
                return null;
        }
    }
}
