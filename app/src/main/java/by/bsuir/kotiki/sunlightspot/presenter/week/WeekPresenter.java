package by.bsuir.kotiki.sunlightspot.presenter.week;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.week.WeekForecastParser;

public final class WeekPresenter {
    private final Fragment fragment;

    public WeekPresenter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void updateForecast() {
        new WeekForecastParser(fragment).execute();
    }
}
