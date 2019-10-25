package com.example.comic.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.comic.mvp.ui.fragment.CalendarFragment;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends FragmentPagerAdapter {

    List<CalendarFragment> mList = new ArrayList<>();
    List<String> name = new ArrayList<>();

    public CalendarAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(CalendarFragment fragment, String title) {
        mList.add(fragment);
        name.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
