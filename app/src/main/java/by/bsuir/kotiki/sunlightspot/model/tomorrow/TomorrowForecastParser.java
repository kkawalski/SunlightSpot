package by.bsuir.kotiki.sunlightspot.model.tomorrow;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.bsuir.kotiki.sunlightspot.R;

public class TomorrowForecastParser extends AsyncTask<Void, Void, String> {
    private final Fragment fragment;

    public TomorrowForecastParser(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultJson = null;
        try {
            int cityId = 625144;

            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&appid=57432abff315a24276715cd1a27b3d18");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.connect();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            resultJson = builder.toString();

        } catch (Exception e) {
            Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String stringForecast) {
        super.onPostExecute(stringForecast);
        if (stringForecast == null) {
            Toast.makeText(fragment.getActivity().getApplicationContext(), "Please, connect to the internet", Toast.LENGTH_LONG).show();
        } else {
            try {
                JSONArray list = new JSONObject(stringForecast).getJSONArray("list");

                double[] temperatures = new double[5];
                String[] states = new String[5];
                double temperature = 0;
                String pressure = "", humidity = "", windDegree = "", windSpeed = "";
                for (int i = 7, j = 0; i < 16; i += 2, j++) {
                    JSONObject currentJsonObject = (JSONObject) list.get(i);
                    temperatures[j] = Double.parseDouble(currentJsonObject.getJSONObject("main").getString("temp")) - 273;
                    states[j] = ((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("main");

                    if (i == 11) {
                        temperature = temperatures[j];
                        pressure = currentJsonObject.getJSONObject("main").getString("pressure");
                        humidity = currentJsonObject.getJSONObject("main").getString("humidity");
                        windDegree = currentJsonObject.getJSONObject("wind").getString("deg");
                        windSpeed = currentJsonObject.getJSONObject("wind").getString("speed");
                    }
                }

                TextView tomorrowTextView = fragment.getView().findViewById(R.id.tomorrowTextView);
                tomorrowTextView.setText(new SimpleDateFormat("dd MMMM").format(new GregorianCalendar().getTime()));

                TextView temperatureTextView = fragment.getView().findViewById(R.id.temperatureTextView);
                temperatureTextView.setText(String.format("%.1f °C", temperature));

                TextView pressureTextView = fragment.getView().findViewById(R.id.pressureTextView);
                pressureTextView.setText(String.format("%.1f '", Double.parseDouble(pressure) * 0.75006375541921));


                TextView humidityTextView = fragment.getView().findViewById(R.id.humidityTextView);
                humidityTextView.setText(humidity + " %");

                TextView windDegreeTextView = fragment.getView().findViewById(R.id.windDegreeTextView);
                windDegreeTextView.setText(windDegree);

                TextView windSpeedTextView = fragment.getView().findViewById(R.id.windSpeedTextView);
                windSpeedTextView.setText(windSpeed + " m/s");


                TextView temperature1TextView = fragment.getView().findViewById(R.id.temperature1TextView);
                temperature1TextView.setText(String.format("%.1f", temperatures[0]));

                TextView temperature2TextView = fragment.getView().findViewById(R.id.temperature2TextView);
                temperature2TextView.setText(String.format("%.1f", temperatures[1]));

                TextView temperature3TextView = fragment.getView().findViewById(R.id.temperature3TextView);
                temperature3TextView.setText(String.format("%.1f", temperatures[2]));

                TextView temperature4TextView = fragment.getView().findViewById(R.id.temperature4TextView);
                temperature4TextView.setText(String.format("%.1f", temperatures[3]));

                TextView temperature5TextView = fragment.getView().findViewById(R.id.temperature5TextView);
                temperature5TextView.setText(String.format("%.1f", temperatures[4]));
            } catch (Exception e) {
                Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
