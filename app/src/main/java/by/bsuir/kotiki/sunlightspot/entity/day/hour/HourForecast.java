package by.bsuir.kotiki.sunlightspot.entity.day.hour;

import java.util.Arrays;

public class HourForecast {
    private double[] temperatures;
    private String[] states;
    private int[] statesId;

    public HourForecast() {
    }

    public HourForecast(double[] temperatures, String[] states, int[] statesId) {
        this.temperatures = temperatures;
        this.states = states;
        this.statesId = statesId;
    }

    public double[] getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(double[] temperatures) {
        this.temperatures = temperatures;
    }

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }

    public int[] getStatesId() {
        return statesId;
    }

    public void setStatesId(int[] statesId) {
        this.statesId = statesId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        HourForecast that = (HourForecast) obj;

        if (!Arrays.equals(temperatures, that.temperatures)) { return false; }
        if (!Arrays.equals(states, that.states)) { return false; }
        if (!Arrays.equals(statesId, that.statesId)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(temperatures);
        result = 31 * result + Arrays.hashCode(states);
        result = 31 * result + Arrays.hashCode(statesId);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" +
                "temperatures=" + Arrays.toString(temperatures) +
                ", states=" + Arrays.toString(states) +
                ", statesId=" + Arrays.toString(statesId) +
                '}';
    }
}
