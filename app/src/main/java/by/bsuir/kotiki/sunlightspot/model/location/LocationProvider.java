package by.bsuir.kotiki.sunlightspot.model.location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class LocationProvider {
    private final static LocationProvider instance = new LocationProvider();

    private LocationProvider() {
    }

    public List<String> getLocations(String param) throws Exception {
        List<String> result = new ArrayList<>();

        String jsonResult = performRequest(param);
        JSONArray array = new JSONObject(jsonResult).getJSONArray("predictions");

        for (int i = 0; i < 5; i++) {
            String value = array.getJSONObject(0).getString("description");
            if (!result.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    private String performRequest(String param) throws Exception {
        String resultJson = null;

        URL url = new URL(String.format("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&types=(cities)&regions=locality&language=en&key=AIzaSyC9_4QA8rEHKMcsMG700uPTOxvqEz3oxCE", param));
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

        return resultJson;
    }

    public static LocationProvider getInstance() {
        return instance;
    }
}
