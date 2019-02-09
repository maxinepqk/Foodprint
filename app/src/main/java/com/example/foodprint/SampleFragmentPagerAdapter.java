package com.example.foodprint;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "calendar", "scanner", "camera", "list" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return CalendarFragment.newInstance(position);
        else if (position == 1) {
            ScannerFragment s = ScannerFragment.newInstance(position);
            RetrieveFeedTask testing = new RetrieveFeedTask();
            testing.execute();
            return s;

        }
        else if (position == 2) return CVFragment.newInstance(position);
        else return ListFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

    }

}
