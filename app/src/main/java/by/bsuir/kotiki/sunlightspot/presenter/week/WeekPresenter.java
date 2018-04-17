package by.bsuir.kotiki.sunlightspot.presenter.week;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.week.WeekForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.week.WeekForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;
import by.bsuir.kotiki.sunlightspot.view.week.WeekForecastFragment;

public final class WeekPresenter implements Presenter {
    private final WeekForecastFragment fragment;

    public WeekPresenter(Fragment fragment) {
        this.fragment = (WeekForecastFragment) fragment;
    }

    public void updateForecast() {
        new WeekForecastParser(this).execute();
    }

    @Override
    public void updateForecast(Forecast forecast) {
        if (forecast != null) {
            fragment.setData((WeekForecast) forecast);
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        if (message != null) {
            fragment.displayMessage(message);
        }
    }
}
