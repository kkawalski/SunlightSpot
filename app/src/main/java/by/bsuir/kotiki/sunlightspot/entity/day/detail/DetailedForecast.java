package by.bsuir.kotiki.sunlightspot.entity.day.detail;

public class DetailedForecast {
    private String state;
    private int stateId;
    private double temperature;
    private int humidity;
    private double pressure;
    private double windDegree;
    private double windSpeed;

    private DetailedForecast() {
    }

    public static class Builder {
        private String state;
        private int stateId;
        private double temperature;
        private int humidity;
        private double pressure;
        private double windDegree;
        private double windSpeed;

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder stateId(int stateId) {
            this.stateId = stateId;
            return this;
        }

        public Builder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder pressure(double pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder windDegree(double windDegree) {
            this.windDegree = windDegree;
            return this;
        }

        public Builder windSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public DetailedForecast build() {
            DetailedForecast forecast = new DetailedForecast();
            forecast.setState(state);
            forecast.setStateId(stateId);
            forecast.setTemperature(temperature);
            forecast.setHumidity(humidity);
            forecast.setPressure(pressure);
            forecast.setWindDegree(windDegree);
            forecast.setWindSpeed(windSpeed);
            return forecast;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        DetailedForecast that = (DetailedForecast) obj;

        if (state == null || !state.equals(that.state)) { return false; }
        if (stateId != that.stateId) { return false; }
        if (temperature != that.temperature) { return false; }
        if (humidity != that.humidity) { return false; }
        if (pressure != that.pressure) { return false; }
        if (windDegree != that.windDegree) { return false; }
        if (windSpeed != that.windSpeed) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + stateId;
        result = 31 * result + (int) temperature;
        result = 31 * result + humidity;
        result = 31 * result + (int) pressure;
        result = 31 * result + (int) windDegree;
        result = 31 * result + (int) windSpeed;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" +
                "state='" + state + '\'' +
                ", stateId=" + stateId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windDegree=" + windDegree +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
