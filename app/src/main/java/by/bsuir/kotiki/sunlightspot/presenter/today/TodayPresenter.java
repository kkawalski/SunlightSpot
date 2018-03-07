package by.bsuir.kotiki.sunlightspot.presenter.today;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.today.HourForecastParser;
import by.bsuir.kotiki.sunlightspot.model.today.TodayForecastParser;

public final class TodayPresenter {
    private final TodayForecastParser generalParser;
    private final HourForecastParser hourParser;

    public TodayPresenter(Fragment fragment) {
        generalParser = new TodayForecastParser(fragment);
        hourParser = new HourForecastParser(fragment);
    }

    public void updateForecast() {
        generalParser.execute();
        hourParser.execute();
    }
}
