package by.bsuir.kotiki.sunlightspot.entity.day;

import java.util.Date;

import by.bsuir.kotiki.sunlightspot.entity.Forecast;
import by.bsuir.kotiki.sunlightspot.entity.day.detail.DetailedForecast;
import by.bsuir.kotiki.sunlightspot.entity.day.hour.HourForecast;

public class DayForecast implements Forecast {
    private Date date;
    private DetailedForecast detailedForecast;
    private HourForecast hourForecast;

    public DayForecast() {
    }

    public DayForecast(Date date, DetailedForecast detailedForecast, HourForecast hourForecast) {
        this.date = date;
        this.detailedForecast = detailedForecast;
        this.hourForecast = hourForecast;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DetailedForecast getDetailedForecast() {
        return detailedForecast;
    }

    public void setDetailedForecast(DetailedForecast detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    public HourForecast getHourForecast() {
        return hourForecast;
    }

    public void setHourForecast(HourForecast hourForecast) {
        this.hourForecast = hourForecast;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        DayForecast that = (DayForecast) obj;

        if (date != null ? !date.equals(that.date) : that.date != null){ return false; }
        if (detailedForecast != null ? !detailedForecast.equals(that.detailedForecast) : that.detailedForecast != null) { return false; }
        if (hourForecast != null ? !hourForecast.equals(that.hourForecast) : that.hourForecast != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (detailedForecast != null ? detailedForecast.hashCode() : 0);
        result = 31 * result + (hourForecast != null ? hourForecast.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" +
                "date=" + date +
                ", detailedForecast=" + detailedForecast +
                ", hourForecast=" + hourForecast +
                '}';
    }
}
