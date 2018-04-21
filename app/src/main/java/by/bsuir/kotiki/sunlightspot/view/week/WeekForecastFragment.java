package by.bsuir.kotiki.sunlightspot.view.week;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.entity.week.WeekForecast;
import by.bsuir.kotiki.sunlightspot.model.icon.IconStorage;
import by.bsuir.kotiki.sunlightspot.presenter.week.WeekPresenter;
import by.bsuir.kotiki.sunlightspot.view.ForecastView;

public class WeekForecastFragment extends Fragment implements ForecastView {
    private WeekPresenter presenter;
    private final IconStorage iconStorage = IconStorage.getInstance();
    private boolean firstTimeFlag = true;

    private TextView[] dateTextView = new TextView[6];
    private TextView[] forecastTextView = new TextView[6];
    private ImageView[] statusImageView = new ImageView[6];

    public WeekForecastFragment() {
    }

    public static WeekForecastFragment newInstance() {
        return new WeekForecastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set up date text views
        dateTextView[0] = getView().findViewById(R.id.date1TextView);
        dateTextView[1] = getView().findViewById(R.id.date2TextView);
        dateTextView[2] = getView().findViewById(R.id.date3TextView);
        dateTextView[3] = getView().findViewById(R.id.date4TextView);
        dateTextView[4] = getView().findViewById(R.id.date5TextView);
        dateTextView[5] = getView().findViewById(R.id.date6TextView);

        // set up forecast text views
        forecastTextView[0] = getView().findViewById(R.id.forecast1TextView);
        forecastTextView[1] = getView().findViewById(R.id.forecast2TextView);
        forecastTextView[2] = getView().findViewById(R.id.forecast3TextView);
        forecastTextView[3] = getView().findViewById(R.id.forecast4TextView);
        forecastTextView[4] = getView().findViewById(R.id.forecast5TextView);
        forecastTextView[5] = getView().findViewById(R.id.forecast6TextView);

        // set up status image views
        statusImageView[0] = getView().findViewById(R.id.status1ImageView);
        statusImageView[1] = getView().findViewById(R.id.status2ImageView);
        statusImageView[2] = getView().findViewById(R.id.status3ImageView);
        statusImageView[3] = getView().findViewById(R.id.status4ImageView);
        statusImageView[4] = getView().findViewById(R.id.status5ImageView);
        statusImageView[5] = getView().findViewById(R.id.status6ImageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new WeekPresenter(this);
        presenter.updateForecast();
    }

    public void displayMessage(String message) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    public void setData(WeekForecast forecast) {
        // set dates
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", new Locale("en"));
        Calendar calendar = new GregorianCalendar();
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, 1);
            dateTextView[i].setText(sdf.format(calendar.getTime()));
        }

        // set forecast
        String[] states = forecast.getStates();
        double[] temperatures = forecast.getTemperatures();
        for (int i = 0; i < 6; i++) {
            forecastTextView[i].setText(String.format("%.1f °C, %s", temperatures[i], states[i]));
        }

        // set icons
        int[] statesId = forecast.getStatesId();
        for (int i = 0; i < 6; i++) {
            statusImageView[i].setImageDrawable(iconStorage.getIcon(statesId[i], getContext()));
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
