package by.bsuir.kotiki.sunlightspot.presenter.tomorrow;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.model.location.LocationManager;
import by.bsuir.kotiki.sunlightspot.model.parser.tomorrow.TomorrowForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;
import by.bsuir.kotiki.sunlightspot.view.tomorrow.TomorrowForecastFragment;

public final class TomorrowPresenter implements ForecastPresenter {
    private final TomorrowForecastFragment fragment;
    private final LocationManager locationManager;

    public TomorrowPresenter(Fragment fragment) {
        this.fragment = (TomorrowForecastFragment) fragment;
        this.locationManager = LocationManager.getInstance(fragment.getActivity().getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void updateForecast() {
        new TomorrowForecastParser(this, locationManager.getLocationParam()).execute();
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
