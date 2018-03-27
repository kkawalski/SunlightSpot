package by.bsuir.kotiki.sunlightspot.presenter.today;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.today.HourForecastParser;
import by.bsuir.kotiki.sunlightspot.model.today.TodayForecastParser;

public final class TodayPresenter {
    private final Fragment fragment;

    public TodayPresenter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void updateForecast() {
        new TodayForecastParser(fragment).execute();
        new HourForecastParser(fragment).execute();
    }
}
