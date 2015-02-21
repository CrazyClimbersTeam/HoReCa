package com.crazyclimbersteam.horeca.fragment.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * @author Mirash
 */
public class SettingsFragment extends BaseFragment {
    public static String TAG = getFragmentTag(SettingsFragment.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }
}
