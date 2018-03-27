package by.bsuir.kotiki.sunlightspot.presenter.tomorrow;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.tomorrow.TomorrowForecastParser;

public final class TomorrowPresenter {
    private final Fragment fragment;

    public TomorrowPresenter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void updateForecast() {
        new TomorrowForecastParser(fragment).execute();
    }
}
