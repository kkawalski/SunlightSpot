package by.bsuir.kotiki.sunlightspot.model.parser.today;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.Parser;
import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;

public class HourForecastParser extends Parser {
    private final DetailedForecast detailedForecast;

    public HourForecastParser(ForecastPresenter presenter, DetailedForecast detailedForecast, String location) {
        super(presenter, String.format("http://api.openweathermap.org/data/2.5/forecast?%sappid=57432abff315a24276715cd1a27b3d18", location));
        this.detailedForecast = detailedForecast;
    }

    @Override
    protected void onPostExecute(String stringForecast) {
        super.onPostExecute(stringForecast);

        if (stringForecast == null) {
            presenter.displayErrorMessage("Please connect to the Internet");
            return;
        }

        try {
            JSONArray list = new JSONObject(stringForecast).getJSONArray("list");

            double[] temperatures = new double[5];
            String[] states = new String[5];
            int[] statesId = new int[5];
            String city = new JSONObject(stringForecast).getJSONObject("city").getString("name");

            for (int i = 0, j = 0; i < 9; i += 2, j++) {
                JSONObject currentJsonObject = (JSONObject) list.get(i);
                temperatures[j] = Double.parseDouble(currentJsonObject.getJSONObject("main").getString("temp")) - 273;
                statesId[j] = Integer.parseInt(((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("id"));
            }

            HourForecast hourForecast = new HourForecast(city, temperatures, states, statesId);
            DayForecast forecast = new DayForecast(new Date(), detailedForecast, hourForecast);

            presenter.updateForecast(forecast);

        } catch (Exception e) {
            presenter.displayErrorMessage("Application error");
        }
    }
}
