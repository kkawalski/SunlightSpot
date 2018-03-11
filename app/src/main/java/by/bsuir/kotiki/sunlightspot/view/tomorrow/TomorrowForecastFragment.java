package by.bsuir.kotiki.sunlightspot.view.tomorrow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.presenter.tomorrow.TomorrowPresenter;

public class TomorrowForecastFragment extends Fragment {
    private final TomorrowPresenter presenter = new TomorrowPresenter(this);

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
        View view = inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false);
        presenter.updateForecast();
        return view;
    }
}
