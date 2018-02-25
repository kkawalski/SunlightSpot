package by.bsuir.kotiki.sunlightspot.view.week;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import by.bsuir.kotiki.sunlightspot.R;

public class WeekForecastFragment extends Fragment {
    public WeekForecastFragment() {}

    public static WeekForecastFragment newInstance(Map<String, String> params) {
        WeekForecastFragment fragment = new WeekForecastFragment();

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
        return inflater.inflate(R.layout.fragment_week_forecast, container, false);
    }
}
