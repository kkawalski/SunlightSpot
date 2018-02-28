package by.bsuir.kotiki.sunlightspot.model.today;

public class TodayForecast {
    private final String currentState;
    private final int currentTemperature;
    private final int currentHumidity;
    private final int currentPressure;
    private final int currentWindDirection;
    private final int currentWindSpeed;

    public TodayForecast(String currentState, int currentTemperature, int currentHumidity, int currentPressure, int currentWindDirection, int currentWindSpeed) {
        this.currentState = currentState;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentPressure = currentPressure;
        this.currentWindDirection = currentWindDirection;
        this.currentWindSpeed = currentWindSpeed;
    }

    public String getCurrentState() {
        return currentState;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public int getCurrentPressure() {
        return currentPressure;
    }

    public int getCurrentWindDirection() {
        return currentWindDirection;
    }

    public int getCurrentWindSpeed() {
        return currentWindSpeed;
    }
}
