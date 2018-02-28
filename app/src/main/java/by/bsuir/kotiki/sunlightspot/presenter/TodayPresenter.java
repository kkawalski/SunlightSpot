package by.bsuir.kotiki.sunlightspot.presenter;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.today.TodayForecastParser;

public final class TodayPresenter {
    private final TodayForecastParser parser;

    public TodayPresenter(Fragment fragment) {
        parser = new TodayForecastParser(fragment);
    }

    public void updateForecast() {
        parser.execute();
    }
}
