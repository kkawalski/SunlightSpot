package by.bsuir.kotiki.sunlightspot.view.today;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.entity.day.DayForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;
import by.bsuir.kotiki.sunlightspot.model.animal.AnimalStorage;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;
import by.bsuir.kotiki.sunlightspot.presenter.today.TodayPresenter;
import by.bsuir.kotiki.sunlightspot.view.ForecastView;

public class TodayForecastFragment extends Fragment implements ForecastView {
    private TodayPresenter presenter;
    private final IconStorage iconStorage = IconStorage.getInstance();
    private final AnimalStorage animalStorage = AnimalStorage.getInstance();
    private boolean firstTimeFlag = true;

    private ImageView detailedAnimalImageView;
    private ImageView detailedStateImageView;
    private TextView detailedStateTextView;
    private TextView detailedTemperatureTextView;
    private TextView detailedPressureTextView;
    private TextView detailedHumidityTextView;
    private TextView detailedWindDegreeTextView;
    private TextView detailedWindSpeedTextView;
    private TextView detailedCityTextView;

    private TextView[] forecastTextView = new TextView[5];
    private ImageView[] statusImageView = new ImageView[5];

    public TodayForecastFragment() {
    }

    public static TodayForecastFragment newInstance() {
        return new TodayForecastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_forecast, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set up detailed forecast views
        detailedAnimalImageView = getView().findViewById(R.id.animalImageView);
        detailedStateImageView = getView().findViewById(R.id.currentStateImageView);
        detailedStateTextView = getView().findViewById(R.id.currentStateTextView);
        detailedTemperatureTextView = getView().findViewById(R.id.currentTemperatureTextView);
        detailedPressureTextView = getView().findViewById(R.id.pressureTextView);
        detailedHumidityTextView = getView().findViewById(R.id.humidityTextView);
        detailedWindDegreeTextView = getView().findViewById(R.id.windTextView);
        detailedWindSpeedTextView = getView().findViewById(R.id.speedTextView);
        detailedCityTextView = getView().findViewById(R.id.cityTextView);

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new TodayPresenter(this);
        presenter.updateForecast();
    }

    public void displayMessage(String message) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    public void setData(DayForecast forecast) {
        DetailedForecast detailedForecast = forecast.getDetailedForecast();
        HourForecast hourForecast = forecast.getHourForecast();

        //set detailed forecast data
        detailedAnimalImageView.setImageDrawable(animalStorage.getAnimal(detailedForecast.getStateId(), getContext()));
        detailedStateImageView.setImageDrawable(iconStorage.getIcon(detailedForecast.getStateId(), getContext()));
        detailedStateTextView.setText(detailedForecast.getState());
        detailedTemperatureTextView.setText(String.format("%.1f °C", detailedForecast.getTemperature()));
        detailedPressureTextView.setText(String.format("%.1f '", detailedForecast.getPressure()));
        detailedHumidityTextView.setText(detailedForecast.getHumidity() + " %");
        detailedWindDegreeTextView.setText(detailedForecast.getWindDegree() + " °");
        detailedWindSpeedTextView.setText(detailedForecast.getWindSpeed() + " m/s");
        detailedCityTextView.setText(hourForecast.getCity());

        // set hour forecast data
        int[] statesId = hourForecast.getStatesId();
        double[] temperatures = hourForecast.getTemperatures();
        for (int i = 0; i < 5; i++) {
            statusImageView[i].setImageDrawable(iconStorage.getIcon(statesId[i], getContext()));
            forecastTextView[i].setText(String.format("%.1f °C", temperatures[i]));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void reloadForecast() {
        if (!firstTimeFlag) {
            presenter.updateForecast();
        } else {
            firstTimeFlag = false;
        }
    }
}
