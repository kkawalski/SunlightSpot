package by.bsuir.kotiki.sunlightspot.view.pager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.kotiki.sunlightspot.view.ForecastView;
import by.bsuir.kotiki.sunlightspot.view.settings.SettingsFragment;
import by.bsuir.kotiki.sunlightspot.view.today.TodayForecastFragment;
import by.bsuir.kotiki.sunlightspot.view.tomorrow.TomorrowForecastFragment;
import by.bsuir.kotiki.sunlightspot.view.week.WeekForecastFragment;

public class ForecastPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ForecastPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(SettingsFragment.newInstance());
        fragments.add(TodayForecastFragment.newInstance());
        fragments.add(TomorrowForecastFragment.newInstance());
        fragments.add(WeekForecastFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);

        if (fragment.getClass() != SettingsFragment.class) {
            ForecastView forecastView = (ForecastView) fragment;
            forecastView.reloadForecast();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
