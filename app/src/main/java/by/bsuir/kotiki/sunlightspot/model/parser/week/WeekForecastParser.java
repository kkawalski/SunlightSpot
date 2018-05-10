package by.bsuir.kotiki.sunlightspot.model.parser.week;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.week.WeekForecast;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;
import by.bsuir.kotiki.sunlightspot.model.parser.Parser;
import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;

public class WeekForecastParser extends Parser {
    private static final IconStorage iconStorage = IconStorage.getInstance();

    public WeekForecastParser(ForecastPresenter presenter, String location) {
        super(presenter, String.format("http://api.openweathermap.org/data/2.5/forecast/daily?%sappid=57432abff315a24276715cd1a27b3d18", location));
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

            double[] temperatures = new double[6];
            String[] states = new String[6];
            int[] statesId = new int[6];

            for (int i = 0; i < 6; i++) {
                JSONObject currentJsonObject = (JSONObject) list.get(i);
                temperatures[i] = Double.parseDouble(currentJsonObject.getJSONObject("temp").getString("day")) - 273;
                states[i] = ((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("main");
                statesId[i] = Integer.parseInt(((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("id"));
            }

            WeekForecast forecast = new WeekForecast(new Date(), states, statesId, temperatures);
            presenter.updateForecast(forecast);

        } catch (Exception e) {
            presenter.displayErrorMessage("Application error");
        }
    }
}
