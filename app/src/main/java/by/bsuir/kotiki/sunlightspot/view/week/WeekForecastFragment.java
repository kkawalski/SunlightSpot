package by.bsuir.kotiki.sunlightspot.view.week;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.presenter.week.WeekPresenter;

public class WeekForecastFragment extends Fragment {
    private final WeekPresenter presenter = new WeekPresenter(this);

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
        View view = inflater.inflate(R.layout.fragment_week_forecast, container, false);
        presenter.updateForecast();
        return view;
    }
}
