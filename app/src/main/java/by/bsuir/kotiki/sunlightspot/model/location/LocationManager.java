package by.bsuir.kotiki.sunlightspot.model.location;

import android.content.Context;
import android.location.Location;

public final class LocationManager {
    private static LocationManager instance;
    private final LocationTracker tracker;
    private boolean auto = true;

    private LocationManager(Context context) {
        this.tracker = new LocationTracker(context);
    }

    public String getLocationParam() {
        String param = "";

        if (auto) {
            Location location = tracker.getLocation();
            if (location != null) {
                param = String.format("lat=%s&lon=%s&", location.getLatitude(), location.getLongitude());
            }
        }

        return param;
    }

    public static LocationManager getInstance(Context context) {
        if (instance == null) {
            instance = new LocationManager(context);
        }

        return instance;
    }

    public static LocationManager getInstance() {
        return instance;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }
}
