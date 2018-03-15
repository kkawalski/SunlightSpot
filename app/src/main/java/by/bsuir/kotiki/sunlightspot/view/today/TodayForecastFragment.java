package by.bsuir.kotiki.sunlightspot.view.today;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.presenter.today.TodayPresenter;

public class TodayForecastFragment extends Fragment {
    private final TodayPresenter presenter = new TodayPresenter(this);

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
        presenter.updateForecast();
        return view;
    }
}
