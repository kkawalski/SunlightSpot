package by.bsuir.kotiki.sunlightspot.presenter.week;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.week.WeekForecastParser;

public final class WeekPresenter {
    private final WeekForecastParser parser;

    public WeekPresenter(Fragment fragment) {
        parser = new WeekForecastParser(fragment);
    }

    public void updateForecast() {
        parser.execute();
    }
}
