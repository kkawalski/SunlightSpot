package by.bsuir.kotiki.sunlightspot.model.parser;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import by.bsuir.kotiki.sunlightspot.presenter.ForecastPresenter;

public abstract class Parser extends AsyncTask<Void, Void, String> {
    protected final ForecastPresenter presenter;
    protected final String url;

    protected Parser(ForecastPresenter presenter, String url) {
        this.presenter = presenter;
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultJson = null;
        try {
            URL url = new URL(this.url);
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
            presenter.displayErrorMessage("Application error");
        }
        return resultJson;
    }
}
