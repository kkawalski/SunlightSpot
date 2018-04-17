package by.bsuir.kotiki.sunlightspot.presenter.today;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.today.TodayForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;
import by.bsuir.kotiki.sunlightspot.view.today.TodayForecastFragment;

public final class TodayPresenter implements Presenter {
    private final TodayForecastFragment fragment;

    public TodayPresenter(Fragment fragment) {
        this.fragment = (TodayForecastFragment) fragment;
    }

    public void updateForecast() {
        new TodayForecastParser(this).execute();
    }

    @Override
    public void updateForecast(Forecast forecast) {
        if (forecast != null) {
            fragment.setData((DayForecast) forecast);
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        if (message != null) {
            fragment.displayMessage(message);
        }
    }
}
