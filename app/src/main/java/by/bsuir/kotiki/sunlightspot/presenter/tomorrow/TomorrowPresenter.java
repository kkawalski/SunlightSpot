package by.bsuir.kotiki.sunlightspot.presenter.tomorrow;

import android.support.v4.app.Fragment;

import by.bsuir.kotiki.sunlightspot.model.tomorrow.TomorrowForecastParser;

public final class TomorrowPresenter {
    private final TomorrowForecastParser parser;

    public TomorrowPresenter(Fragment fragment) {
        parser = new TomorrowForecastParser(fragment);
    }

    public void updateForecast() {
        parser.execute();
    }
}
