package by.bsuir.kotiki.sunlightspot.presenter.week;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.week.WeekForecast;
import by.bsuir.kotiki.sunlightspot.model.location.LocationManager;
import by.bsuir.kotiki.sunlightspot.model.parser.week.WeekForecastParser;
import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;
import by.bsuir.kotiki.sunlightspot.view.week.WeekForecastFragment;

public final class WeekPresenter implements ForecastPresenter {
    private final WeekForecastFragment fragment;
    private final LocationManager locationManager;

    public WeekPresenter(Fragment fragment) {
        this.fragment = (WeekForecastFragment) fragment;
        this.locationManager = LocationManager.getInstance(fragment.getActivity().getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void updateForecast() {
        new WeekForecastParser(this, locationManager.getLocationParam()).execute();
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
