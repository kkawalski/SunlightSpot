package by.bsuir.kotiki.sunlightspot.model.week;

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

public class WeekForecastParser extends AsyncTask<Void, Void, String> {
    private final Fragment fragment;

    public WeekForecastParser(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultJson = null;
        try {
            int cityId = 625144;

            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=" + cityId + "&appid=57432abff315a24276715cd1a27b3d18");
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

                double[] temperatures = new double[6];
                String[] states = new String[6];
                for (int i = 0; i < 6; i++) {
                    JSONObject currentJsonObject = (JSONObject) list.get(i);
                    temperatures[i] = Double.parseDouble(currentJsonObject.getJSONObject("temp").getString("day")) - 273;
                    states[i] = ((JSONObject) currentJsonObject.getJSONArray("weather").get(0)).getString("main");
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM");
                Calendar calendar = new GregorianCalendar();

                TextView date1TextView = fragment.getView().findViewById(R.id.date1TextView);
                calendar.add(Calendar.DATE, 1);
                date1TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast1TextView = fragment.getView().findViewById(R.id.forecast1TextView);
                forecast1TextView.setText(String.format("%.1f °C, %s", temperatures[0], states[0]));

                TextView date2TextView = fragment.getView().findViewById(R.id.date2TextView);
                calendar.add(Calendar.DATE, 1);
                date2TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast2TextView = fragment.getView().findViewById(R.id.forecast2TextView);
                forecast2TextView.setText(String.format("%.1f °C, %s", temperatures[1], states[1]));

                TextView date3TextView = fragment.getView().findViewById(R.id.date3TextView);
                calendar.add(Calendar.DATE, 1);
                date3TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast3TextView = fragment.getView().findViewById(R.id.forecast3TextView);
                forecast3TextView.setText(String.format("%.1f °C, %s", temperatures[2], states[2]));

                TextView date4TextView = fragment.getView().findViewById(R.id.date4TextView);
                calendar.add(Calendar.DATE, 1);
                date4TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast4TextView = fragment.getView().findViewById(R.id.forecast4TextView);
                forecast4TextView.setText(String.format("%.1f °C, %s", temperatures[3], states[3]));

                TextView date5TextView = fragment.getView().findViewById(R.id.date5TextView);
                calendar.add(Calendar.DATE, 1);
                date5TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast5TextView = fragment.getView().findViewById(R.id.forecast5TextView);
                forecast5TextView.setText(String.format("%.1f °C, %s", temperatures[4], states[4]));

                TextView date6TextView = fragment.getView().findViewById(R.id.date6TextView);
                calendar.add(Calendar.DATE, 1);
                date6TextView.setText(sdf.format(calendar.getTime()));

                TextView forecast6TextView = fragment.getView().findViewById(R.id.forecast6TextView);
                forecast6TextView.setText(String.format("%.1f °C, %s", temperatures[5], states[5]));

            } catch (Exception e) {
                Toast.makeText(fragment.getActivity().getApplicationContext(), "Application error", Toast.LENGTH_LONG).show();
            }
        }

    }
}
