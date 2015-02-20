package com.crazyclimbersteam.horeca.fragment.base.drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;


/**
 * @author Mirash
 */
//TODO implement me
public class NavigationMenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.navigation_menu, null);
        return content;
    }
}
