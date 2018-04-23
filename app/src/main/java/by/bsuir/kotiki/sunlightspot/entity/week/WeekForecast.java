package by.bsuir.kotiki.sunlightspot.entity.week;

import java.util.Arrays;
import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;

public class WeekForecast implements Forecast {
    private Date date;
    private String[] states;
    private int[] statesId;
    private double[] temperatures;

    public WeekForecast() {
    }

    public WeekForecast(Date date, String[] states, int[] statesId, double[] temperatures) {
        this.date = date;
        this.states = states;
        this.statesId = statesId;
        this.temperatures = temperatures;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double[] getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(double[] temperatures) {
        this.temperatures = temperatures;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        WeekForecast that = (WeekForecast) obj;

        if (date != null ? !date.equals(that.date) : that.date != null) { return false; }
        if (!Arrays.equals(states, that.states)) { return false; }
        if (!Arrays.equals(statesId, that.statesId)) { return false; }
        if (!Arrays.equals(temperatures, that.temperatures)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(states);
        result = 31 * result + Arrays.hashCode(statesId);
        result = 31 * result + Arrays.hashCode(temperatures);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" +
                "date=" + date +
                ", states=" + Arrays.toString(states) +
                ", statesId=" + Arrays.toString(statesId) +
                ", temperatures=" + Arrays.toString(temperatures) +
                '}';
    }
}
