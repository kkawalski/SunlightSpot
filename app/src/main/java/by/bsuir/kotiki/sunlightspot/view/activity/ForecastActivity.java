package by.bsuir.kotiki.sunlightspot.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.bsuir.kotiki.sunlightspot.R;
import by.bsuir.kotiki.sunlightspot.view.pager.ForecastPagerAdapter;

public class ForecastActivity extends AppCompatActivity {
    private ForecastPagerAdapter forecastPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecastPagerAdapter = new ForecastPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(forecastPagerAdapter);
    }
}
