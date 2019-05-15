package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpMainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>fragments;
    private ArrayList<String>tablist;

    public VpMainAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tablist) {
        super(fm);
        this.fragments = fragments;
        this.tablist = tablist;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
