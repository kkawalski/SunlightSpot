package by.bsuir.kotiki.sunlightspot.presenter;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;

public interface Presenter {
    void updateForecast(Forecast forecast);
    void displayErrorMessage(String message);
}
