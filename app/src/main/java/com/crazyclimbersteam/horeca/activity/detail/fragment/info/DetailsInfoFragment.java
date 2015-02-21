package com.crazyclimbersteam.horeca.activity.detail.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.fragment.DetailsTabFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.view.DetailsInfoContactsView;

/**
 * @author Mirash
 */
public class DetailsInfoFragment extends DetailsTabFragment {
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private RatingBar mRatingBarView;
    private ImageView mMapPreview;
    private DetailsInfoContactsView mContactsView;
    private View mMenuButton;
    private View mOrderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab_info, null);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mTitleTextView = (TextView) rootView.findViewById(R.id.details_info_title);
        mContentTextView = (TextView) rootView.findViewById(R.id.details_info_description);
        mRatingBarView = (RatingBar) rootView.findViewById(R.id.details_info_rating_bar);
        mMapPreview = (ImageView) rootView.findViewById(R.id.details_info_map_preview);
        mContactsView = (DetailsInfoContactsView) rootView.findViewById(R.id.details_info_contacts_view);
    }

    //TODO
    private void applyData() {
    }

}
