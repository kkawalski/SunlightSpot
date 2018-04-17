package by.bsuir.kotiki.sunlightspot.presenter.tomorrow;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.tomorrow.TomorrowForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;
import by.bsuir.kotiki.sunlightspot.view.tomorrow.TomorrowForecastFragment;

public final class TomorrowPresenter implements Presenter {
    private final TomorrowForecastFragment fragment;

    public TomorrowPresenter(Fragment fragment) {
        this.fragment = (TomorrowForecastFragment) fragment;
    }

    public void updateForecast() {
        new TomorrowForecastParser(this).execute();
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
