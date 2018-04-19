package by.bsuir.kotiki.sunlightspot.presenter.today;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.model.location.LocationManager;
import by.bsuir.kotiki.sunlightspot.model.parser.today.TodayForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;
import by.bsuir.kotiki.sunlightspot.view.today.TodayForecastFragment;

public final class TodayPresenter implements ForecastPresenter {
    private final TodayForecastFragment fragment;
    private final LocationManager locationManager;

    public TodayPresenter(Fragment fragment) {
        this.fragment = (TodayForecastFragment) fragment;
        this.locationManager = LocationManager.getInstance(fragment.getActivity().getApplicationContext());
    }

    public void updateForecast() {
        new TodayForecastParser(this, locationManager.getLocationParam()).execute();
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
