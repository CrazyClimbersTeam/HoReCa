package com.crazyclimbersteam.horeca.tools;

import android.support.v4.app.FragmentManager;

import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class ScreenController {
    private FragmentManager fragmentManager;
    private String currentScreenTag;
    private int screenContainerId;

    public ScreenController(FragmentManager fm, int containerId) {
        fragmentManager = fm;
        screenContainerId = containerId;
    }

    public void navigateToScreenFragment(BaseFragment fragment) {
        log("navigateToScreenFragment");
        if (!fragment.getFragmentTag().equals(currentScreenTag)) {
            log("navigateToScreenFragment success");
            fragmentManager.beginTransaction().replace(screenContainerId, fragment).commit();
            currentScreenTag = fragment.getFragmentTag();
        }
    }
}
