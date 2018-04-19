package by.bsuir.kotiki.sunlightspot.view.tomorrow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;
import by.bsuir.kotiki.sunlightspot.presenter.tomorrow.TomorrowPresenter;

public class TomorrowForecastFragment extends Fragment {
    private TomorrowPresenter presenter;
    private final IconStorage iconStorage = IconStorage.getInstance();

    private ImageView detailedStateImageView;
    private TextView detailedTomorrowTextView;
    private TextView detailedTemperatureTextView;
    private TextView detailedPressureTextView;
    private TextView detailedHumidityTextView;
    private TextView detailedWindDegreeTextView;
    private TextView detailedWindSpeedTextView;

    private TextView[] forecastTextView = new TextView[5];
    private ImageView[] statusImageView = new ImageView[5];

    public TomorrowForecastFragment() {
    }

    public static TomorrowForecastFragment newInstance() {
        return new TomorrowForecastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set up detailed forecast views
        detailedStateImageView = getView().findViewById(R.id.stateImageView);
        detailedTomorrowTextView = getView().findViewById(R.id.tomorrowTextView);
        detailedTemperatureTextView = getView().findViewById(R.id.temperatureTextView);
        detailedPressureTextView = getView().findViewById(R.id.pressureTextView);
        detailedHumidityTextView = getView().findViewById(R.id.humidityTextView);
        detailedWindDegreeTextView = getView().findViewById(R.id.windDegreeTextView);
        detailedWindSpeedTextView = getView().findViewById(R.id.windSpeedTextView);

        // set up forecast text views
        forecastTextView[0] = getView().findViewById(R.id.temperature1TextView);
        forecastTextView[1] = getView().findViewById(R.id.temperature2TextView);
        forecastTextView[2] = getView().findViewById(R.id.temperature3TextView);
        forecastTextView[3] = getView().findViewById(R.id.temperature4TextView);
        forecastTextView[4] = getView().findViewById(R.id.temperature5TextView);

        // set up icons
        statusImageView[0] = getView().findViewById(R.id.state1ImageView);
        statusImageView[1] = getView().findViewById(R.id.state2ImageView);
        statusImageView[2] = getView().findViewById(R.id.state3ImageView);
        statusImageView[3] = getView().findViewById(R.id.state4ImageView);
        statusImageView[4] = getView().findViewById(R.id.state5ImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new TomorrowPresenter(this);
        presenter.updateForecast();
    }

    public void displayMessage(String message) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    public void setData(DayForecast forecast) {
        //set detailed forecast data
        DetailedForecast detailedForecast = forecast.getDetailedForecast();
        detailedStateImageView.setImageDrawable(iconStorage.getIcon(detailedForecast.getStateId(), getContext()));
        detailedTomorrowTextView.setText(new SimpleDateFormat("dd MMMM", new Locale("en")).format(new GregorianCalendar().getTime()));
        detailedTemperatureTextView.setText(String.format("%.1f °C", detailedForecast.getTemperature()));
        detailedPressureTextView.setText(String.format("%.1f '", detailedForecast.getPressure()));
        detailedHumidityTextView.setText(detailedForecast.getHumidity() + " %");
        detailedWindDegreeTextView.setText(detailedForecast.getWindDegree() + " °");
        detailedWindSpeedTextView.setText(detailedForecast.getWindSpeed() + " m/s");

        // set hour forecast data
        HourForecast hourForecast = forecast.getHourForecast();
        int[] statesId = hourForecast.getStatesId();
        double[] temperatures = hourForecast.getTemperatures();
        for (int i = 0; i < 5; i++) {
            statusImageView[i].setImageDrawable(iconStorage.getIcon(statesId[i], getContext()));
            forecastTextView[i].setText(String.format("%.1f °C", temperatures[i]));
        }
    }
}
