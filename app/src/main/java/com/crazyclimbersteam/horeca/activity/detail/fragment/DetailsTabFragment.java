package com.crazyclimbersteam.horeca.activity.detail.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;

/**
 * @author Mirash
 */
public abstract class DetailsTabFragment extends Fragment {
    protected DetailsDataProvider mDataProvider;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDataProvider = ((DetailsActivity) activity).getDataProvider();
    }
}
