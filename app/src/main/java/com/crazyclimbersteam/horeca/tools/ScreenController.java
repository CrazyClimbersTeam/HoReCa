package com.crazyclimbersteam.horeca.tools;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.crazyclimbersteam.horeca.R;
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

    public void navigateToScreenFragment(BaseFragment fragment, Bundle parameters, boolean isForward) {
        log("navigateToScreenFragment " + fragment.getFragmentTag());
        if (!fragment.getFragmentTag().equals(currentScreenTag)) {
            int animIn;
            int animOut;
            if (isForward) {
                animIn = R.anim.enter;
                animOut = R.anim.exit;
            } else {
                animIn = R.anim.pop_enter;
                animOut = R.anim.pop_exit;
            }
            log("navigateToScreenFragment success");
            fragmentManager.beginTransaction()
                    .setCustomAnimations(animIn, animOut)
                    .replace(screenContainerId, fragment, fragment.getFragmentTag()).commit();
            currentScreenTag = fragment.getFragmentTag();
        } else {
            BaseFragment existingFragment = (BaseFragment) fragmentManager.findFragmentByTag(currentScreenTag);
            if (parameters != null && existingFragment != null) {
                existingFragment.onNewArguments(parameters);
            }
        }
    }

    public void navigateToScreenFragment(BaseFragment fragment, Bundle parameters) {
        log("navigateToScreenFragment " + fragment.getFragmentTag());
        if (!fragment.getFragmentTag().equals(currentScreenTag)) {
            log("navigateToScreenFragment success");
            fragmentManager.beginTransaction().replace(screenContainerId, fragment, fragment.getFragmentTag()).commit();
            currentScreenTag = fragment.getFragmentTag();
        } else {
            BaseFragment existingFragment = (BaseFragment) fragmentManager.findFragmentByTag(currentScreenTag);
            if (parameters != null && existingFragment != null) {
                existingFragment.onNewArguments(parameters);
            }
        }
    }

    public String getCurrentScreenTag() {
        return currentScreenTag;
    }
}
