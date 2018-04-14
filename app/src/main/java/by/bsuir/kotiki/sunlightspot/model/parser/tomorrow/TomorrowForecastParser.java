package by.bsuir.kotiki.sunlightspot.model.parser.tomorrow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.Parser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;

public class TomorrowForecastParser extends Parser {
    public TomorrowForecastParser(Presenter presenter) {
        super(presenter, "http://api.openweathermap.org/data/2.5/forecast?id=625144&appid=57432abff315a24276715cd1a27b3d18");
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

            DetailedForecast detailedForecast = null;

            for (int i = 7, j = 0; i < 16; i += 2, j++) {
                JSONObject currentJsonObject = (JSONObject) list.get(i);
                temperatures[j] = Double.parseDouble(currentJsonObject.getJSONObject("main").getString("temp")) - 273;
                states[j] = ((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("main");
                statesId[j] = Integer.parseInt(((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("id"));

                if (i == 11) {
                    detailedForecast = parseDetailedForecast(currentJsonObject);
                }
            }

            HourForecast hourForecast = new HourForecast(temperatures, states, statesId);
            DayForecast forecast = new DayForecast(new Date(), detailedForecast, hourForecast);

            presenter.updateForecast(forecast);

        } catch (Exception e) {
            presenter.displayErrorMessage("Application error");
        }
    }

    private DetailedForecast parseDetailedForecast(JSONObject jsonObject) throws JSONException {
        double temperature = Double.parseDouble(jsonObject.getJSONObject("main").getString("temp")) - 273;
        double pressure = Double.parseDouble(jsonObject.getJSONObject("main").getString("pressure")) * 0.75006375541921;
        int humidity = Integer.parseInt(jsonObject.getJSONObject("main").getString("humidity"));
        double windDegree = Double.parseDouble(jsonObject.getJSONObject("wind").getString("deg"));
        double windSpeed = Double.parseDouble(jsonObject.getJSONObject("wind").getString("speed"));
        int stateId = Integer.parseInt(((JSONObject) jsonObject.getJSONArray("weather").get(0)).getString("id"));
        String state = ((JSONObject) jsonObject.getJSONArray("weather").get(0)).getString("main");

        return new DetailedForecast.Builder().state(state).stateId(stateId).temperature(temperature).humidity(humidity).pressure(pressure).windDegree(windDegree).windSpeed(windSpeed).build();
    }
}
