package by.bsuir.kotiki.sunlightspot.presenter.settings;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import java.util.List;

import by.bsuir.kotiki.sunlightspot.model.location.LocationManager;
import by.bsuir.kotiki.sunlightspot.model.location.LocationProvider;
import by.bsuir.kotiki.sunlightspot.presenter.Presenter;
import by.bsuir.kotiki.sunlightspot.view.settings.SettingsFragment;

public final class SettingsPresenter implements Presenter {
    private final SettingsFragment fragment;
    private final LocationManager locationManager;
    private final LocationProvider locationProvider;

    public SettingsPresenter(Fragment fragment) {
        this.fragment = (SettingsFragment) fragment;
        this.locationManager = LocationManager.getInstance(fragment.getActivity().getApplicationContext());
        this.locationManager.setPresenter(this);
        this.locationProvider = LocationProvider.getInstance();
    }

    public void changeLocationMode(boolean auto) {
        locationManager.setAuto(auto);
    }

    public List<String> getLocations(String param) throws Exception {
        return locationProvider.getLocations(param);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getLocationParam() {
        return fragment.getLocationParam();
    }

    @Override
    public void displayErrorMessage(String message) {

    }
}
