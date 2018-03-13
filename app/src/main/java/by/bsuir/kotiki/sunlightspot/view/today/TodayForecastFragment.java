package by.bsuir.kotiki.sunlightspot.view.today;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.presenter.today.TodayPresenter;

public class TodayForecastFragment extends Fragment {
    private final TodayPresenter presenter = new TodayPresenter(this);
    public TodayForecastFragment() {}

    public static TodayForecastFragment newInstance(Map<String, String> params) {
        TodayForecastFragment fragment = new TodayForecastFragment();

        Bundle args = new Bundle();
        for (Map.Entry<String, String> e : params.entrySet()) {
            args.putString(e.getKey(), e.getValue());
        }
        fragment.setArguments(args);

        return fragment;
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
