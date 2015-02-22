package com.crazyclimbersteam.horeca.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:26
 */
public class MenuPagerAdapter extends FragmentPagerAdapter {

    private List<MenuPagerAdapterPage> pages;

    public interface MenuPagerAdapterPage {
        public BaseFragment getFragment();
        public String getTitle();
    }

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
        pages = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void add(MenuPagerAdapterPage page) {
        pages.add(page);
    }
}
