package by.bsuir.kotiki.sunlightspot.view.tomorrow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import by.bsuir.kotiki.sunlightspot.R;

public class TomorrowForecastFragment extends Fragment {
    private String temperature;

    public TomorrowForecastFragment() {}

    public static TomorrowForecastFragment newInstance(Map<String, String> params) {
        TomorrowForecastFragment fragment = new TomorrowForecastFragment();

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

        Bundle arguments = getArguments();
        if (arguments != null) {
            temperature = arguments.getString(TomorrowForecastParams.TEMPERATURE_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false);
    }
}
