package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpBanMiShowAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment>fragments;
    private ArrayList<String>tablist;

    public VpBanMiShowAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tablist) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}
