package by.bsuir.kotiki.sunlightspot.view.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Collections;

import by.bsuir.kotiki.sunlightspot.view.today.TodayForecastFragment;
import by.bsuir.kotiki.sunlightspot.view.tomorrow.TomorrowForecastFragment;
import by.bsuir.kotiki.sunlightspot.view.week.WeekForecastFragment;

public class ForecastPagerAdapter extends FragmentPagerAdapter {
    public ForecastPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return TodayForecastFragment.newInstance(Collections.EMPTY_MAP);
            case 1: return TomorrowForecastFragment.newInstance(Collections.EMPTY_MAP);
            case 2: return WeekForecastFragment.newInstance(Collections.EMPTY_MAP);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
