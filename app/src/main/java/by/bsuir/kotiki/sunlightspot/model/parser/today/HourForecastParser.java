package by.bsuir.kotiki.sunlightspot.model.parser.today;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;
import by.bsuir.kotiki.sunlightspot.model.parser.Parser;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;

public class HourForecastParser extends Parser {
    private final DetailedForecast detailedForecast;

    public HourForecastParser(Presenter presenter, DetailedForecast detailedForecast) {
        super(presenter, "http://api.openweathermap.org/data/2.5/forecast?id=625144&appid=57432abff315a24276715cd1a27b3d18");
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

            for (int i = 0, j = 0; i < 9; i += 2, j++) {
                JSONObject currentJsonObject = (JSONObject) list.get(i);
                temperatures[j] = Double.parseDouble(currentJsonObject.getJSONObject("main").getString("temp")) - 273;
                statesId[j] = Integer.parseInt(((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("id"));
            }

//            ImageView state1ImageView = fragment.getView().findViewById(R.id.state1ImageView);
//            state1ImageView.setImageDrawable(iconStorage.getIcon(statesId[0], fragment.getContext()));
//
//            TextView temperature1TextView = fragment.getView().findViewById(R.id.temperature1TextView);
//            temperature1TextView.setText(String.format("%.1f °C", temperatures[0]));
//
//            ImageView state2ImageView = fragment.getView().findViewById(R.id.state2ImageView);
//            state2ImageView.setImageDrawable(iconStorage.getIcon(statesId[1], fragment.getContext()));
//
//            TextView temperature2TextView = fragment.getView().findViewById(R.id.temperature2TextView);
//            temperature2TextView.setText(String.format("%.1f °C", temperatures[1]));
//
//            ImageView state3ImageView = fragment.getView().findViewById(R.id.state3ImageView);
//            state3ImageView.setImageDrawable(iconStorage.getIcon(statesId[2], fragment.getContext()));
//
//            TextView temperature3TextView = fragment.getView().findViewById(R.id.temperature3TextView);
//            temperature3TextView.setText(String.format("%.1f °C", temperatures[2]));
//
//            ImageView state4ImageView = fragment.getView().findViewById(R.id.state4ImageView);
//            state4ImageView.setImageDrawable(iconStorage.getIcon(statesId[3], fragment.getContext()));
//
//            TextView temperature4TextView = fragment.getView().findViewById(R.id.temperature4TextView);
//            temperature4TextView.setText(String.format("%.1f °C", temperatures[3]));
//
//            ImageView state5ImageView = fragment.getView().findViewById(R.id.state5ImageView);
//            state5ImageView.setImageDrawable(iconStorage.getIcon(statesId[4], fragment.getContext()));
//
//            TextView temperature5TextView = fragment.getView().findViewById(R.id.temperature5TextView);
//            temperature5TextView.setText(String.format("%.1f °C", temperatures[4]));

            HourForecast hourForecast = new HourForecast(temperatures, states, statesId);
            DayForecast forecast = new DayForecast(new Date(), detailedForecast, hourForecast);

            presenter.updateForecast(forecast);

        } catch (Exception e) {
            presenter.displayErrorMessage("Application error");
        }
    }
}
