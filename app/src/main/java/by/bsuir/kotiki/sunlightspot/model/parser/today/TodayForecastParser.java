package by.bsuir.kotiki.sunlightspot.model.parser.today;

import org.json.JSONObject;

import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.Parser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;

public class TodayForecastParser extends Parser {
    public TodayForecastParser(Presenter presenter) {
        super(presenter, "http://api.openweathermap.org/data/2.5/weather?id=625144&appid=57432abff315a24276715cd1a27b3d18");
    }

    @Override
    protected void onPostExecute(String stringForecast) {
        super.onPostExecute(stringForecast);

        if (stringForecast == null) {
            presenter.displayErrorMessage("Please connect to the Internet");
            return;
        }

        JSONObject jsonForecast;
        try {
            jsonForecast = new JSONObject(stringForecast);

            String state = ((JSONObject) jsonForecast.getJSONArray("weather").get(0)).getString("main");
            int stateId = Integer.parseInt(((JSONObject) jsonForecast.getJSONArray("weather").get(0)).getString("id"));
            double temperature = Double.parseDouble(jsonForecast.getJSONObject("main").getString("temp")) - 273;
            int humidity = Integer.parseInt(jsonForecast.getJSONObject("main").getString("humidity"));
            double pressure = Double.parseDouble(jsonForecast.getJSONObject("main").getString("pressure")) * 0.75006375541921;
            double windDegree = Double.parseDouble(jsonForecast.getJSONObject("wind").getString("deg"));
            double windSpeed = Double.parseDouble(jsonForecast.getJSONObject("wind").getString("speed"));


            DetailedForecast detailedForecast = new DetailedForecast.Builder().state(state).stateId(stateId).temperature(temperature).humidity(humidity).pressure(pressure).windDegree(windDegree).windSpeed(windSpeed).build();
            new HourForecastParser(presenter, detailedForecast).execute();

        } catch (Exception e) {
            presenter.displayErrorMessage("Application error");
        }
    }
}
