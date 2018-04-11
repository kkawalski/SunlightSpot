package by.bsuir.kotiki.sunlightspot.model.today;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;

public class HourForecastParser extends AsyncTask<Void, Void, String> {
    private static final IconStorage iconStorage = IconStorage.getInstance();
    private final Fragment fragment;

    public HourForecastParser(Fragment fragment) {
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
            try {
                JSONArray list = new JSONObject(stringForecast).getJSONArray("list");

                double[] temperatures = new double[5];
                String[] states = new String[5];
                int[] statesId = new int[5];
                for (int i = 0, j = 0; i < 9; i += 2, j++) {
                    JSONObject currentJsonObject = (JSONObject) list.get(i);
                    temperatures[j] = Double.parseDouble(currentJsonObject.getJSONObject("main").getString("temp")) - 273;
                    states[j] = ((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("main");
                    statesId[j] = Integer.parseInt(((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("id"));
                }

                ImageView state1ImageView = fragment.getView().findViewById(R.id.state1ImageView);
                state1ImageView.setImageDrawable(iconStorage.getIcon(statesId[0], fragment.getContext()));

                TextView temperature1TextView = fragment.getView().findViewById(R.id.temperature1TextView);
                temperature1TextView.setText(String.format("%.1f °C", temperatures[0]));

                ImageView state2ImageView = fragment.getView().findViewById(R.id.state2ImageView);
                state2ImageView.setImageDrawable(iconStorage.getIcon(statesId[1], fragment.getContext()));

                TextView temperature2TextView = fragment.getView().findViewById(R.id.temperature2TextView);
                temperature2TextView.setText(String.format("%.1f °C", temperatures[1]));

                ImageView state3ImageView = fragment.getView().findViewById(R.id.state3ImageView);
                state3ImageView.setImageDrawable(iconStorage.getIcon(statesId[2], fragment.getContext()));

                TextView temperature3TextView = fragment.getView().findViewById(R.id.temperature3TextView);
                temperature3TextView.setText(String.format("%.1f °C", temperatures[2]));

                ImageView state4ImageView = fragment.getView().findViewById(R.id.state4ImageView);
                state4ImageView.setImageDrawable(iconStorage.getIcon(statesId[3], fragment.getContext()));

                TextView temperature4TextView = fragment.getView().findViewById(R.id.temperature4TextView);
                temperature4TextView.setText(String.format("%.1f °C", temperatures[3]));

                ImageView state5ImageView = fragment.getView().findViewById(R.id.state5ImageView);
                state5ImageView.setImageDrawable(iconStorage.getIcon(statesId[4], fragment.getContext()));

                TextView temperature5TextView = fragment.getView().findViewById(R.id.temperature5TextView);
                temperature5TextView.setText(String.format("%.1f °C", temperatures[4]));
            } catch (Exception e) {
                fragment.getActivity().runOnUiThread(() -> Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show());
            }
        }
    }
}
