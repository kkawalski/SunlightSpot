package by.bsuir.kotiki.sunlightspot.model.today;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.model.animal.AnimalStorage;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;

public class TodayForecastParser extends AsyncTask<Void, Void, String> {
    private final Fragment fragment;
    private static final IconStorage iconStorage = IconStorage.getInstance();
    private static final AnimalStorage animalStorage = AnimalStorage.getInstance();

    public TodayForecastParser(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultJson = null;
        try {
            int cityId = 625144;

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=57432abff315a24276715cd1a27b3d18");
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
            fragment.getActivity().runOnUiThread(() -> Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show());
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String stringForecast) {
        super.onPostExecute(stringForecast);

        if (stringForecast == null) {
            fragment.getActivity().runOnUiThread(() -> Toast.makeText(fragment.getActivity().getApplicationContext(), "Please connect to the Internet", Toast.LENGTH_LONG).show());
        } else {
            JSONObject jsonForecast;
            try {
                jsonForecast = new JSONObject(stringForecast);

                String state = ((JSONObject) jsonForecast.getJSONArray("weather").get(0)).getString("main");
                TextView currentStateTextView = fragment.getView().findViewById(R.id.currentStateTextView);
                currentStateTextView.setText(state);

                int currentStateId = Integer.parseInt(((JSONObject) jsonForecast.getJSONArray("weather").get(0)).getString("id"));
                ImageView currentStateImageView = fragment.getView().findViewById(R.id.currentStateImageView);
                currentStateImageView.setImageDrawable(iconStorage.getIcon(currentStateId, fragment.getContext()));

                ImageView animalImageView = fragment.getView().findViewById(R.id.animalImageView);
                animalImageView.setImageDrawable(animalStorage.getAnimal(currentStateId, fragment.getContext()));

                String temperature = jsonForecast.getJSONObject("main").getString("temp");
                TextView currentTemperatureTextView = fragment.getView().findViewById(R.id.currentTemperatureTextView);
                currentTemperatureTextView.setText(String.format("%.1f °C", Double.parseDouble(temperature) - 273));

                String humidity = jsonForecast.getJSONObject("main").getString("humidity");
                TextView humidityTextView = fragment.getView().findViewById(R.id.humidityTextView);
                humidityTextView.setText(humidity + '%');

                String pressure = jsonForecast.getJSONObject("main").getString("pressure");
                TextView pressureTextView = fragment.getView().findViewById(R.id.pressureTextView);
                pressureTextView.setText(String.format("%.2f '", Double.parseDouble(pressure) * 0.75006375541921));

                String windDegree = jsonForecast.getJSONObject("wind").getString("deg");
                TextView windDegreeTextView = fragment.getView().findViewById(R.id.windTextView);
                windDegreeTextView.setText(windDegree + " °");

                String windSpeed = jsonForecast.getJSONObject("wind").getString("speed");
                TextView windSpeedTextView = fragment.getView().findViewById(R.id.speedTextView);
                windSpeedTextView.setText(windSpeed + "m/s");

                String city = jsonForecast.getString("name");
                TextView cityTextView = fragment.getView().findViewById(R.id.cityTextView);
                cityTextView.setText(city);

            } catch (Exception e) {
                fragment.getActivity().runOnUiThread(() -> Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show());
            }
        }
    }
}
