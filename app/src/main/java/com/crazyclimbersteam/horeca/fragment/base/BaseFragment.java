package com.crazyclimbersteam.horeca.fragment.base;

import android.support.v4.app.Fragment;

/**
 * @author Mirash
 */
public class BaseFragment extends Fragment {
    protected static String getFragmentTag(Class<? extends Fragment> fragmentClass) {
        return fragmentClass.getSimpleName();
    }

    public String getFragmentTag() {
        return getFragmentTag(getClass());
    }
}
