package com.example.dto;

public class WeatherInfo {
    private String weatherText;
    private boolean hasPrecipitation;
    private String precipitationType;
    private boolean isDayTime;
    private TemperatureInfo temperature;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private int visibility;
    private WindInfo wind;
    private long sunrise;
    private long sunset;

    // Default constructor
    public WeatherInfo() {
    }

    // Parameterized constructor
    public WeatherInfo(String weatherText, boolean hasPrecipitation, String precipitationType, boolean isDayTime,
                       TemperatureInfo temperature, double feelsLike, int pressure, int humidity, int visibility,
                       WindInfo wind, long sunrise, long sunset) {
        this.weatherText = weatherText;
        this.hasPrecipitation = hasPrecipitation;
        this.precipitationType = precipitationType;
        this.isDayTime = isDayTime;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.wind = wind;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    // Getters and setters

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public boolean isHasPrecipitation() {
        return hasPrecipitation;
    }

    public void setHasPrecipitation(boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

    public void setPrecipitationType(String precipitationType) {
        this.precipitationType = precipitationType;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    public void setDayTime(boolean dayTime) {
        isDayTime = dayTime;
    }

    public TemperatureInfo getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureInfo temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    // Inner class for temperature information
    public static class TemperatureInfo {
        private double value;
        private String unit;

        // Default constructor
        public TemperatureInfo() {
        }

        // Parameterized constructor
        public TemperatureInfo(double value, String unit) {
            this.value = value;
            this.unit = unit;
        }

        // Getters and setters

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        // toString method
        @Override
        public String toString() {
            return "TemperatureInfo{" +
                    "value=" + value +
                    ", unit='" + unit + '\'' +
                    '}';
        }
    }

    // Inner class for wind information
    public static class WindInfo {
        private double speed;
        private int deg;
        private double gust;

        // Default constructor
        public WindInfo() {
        }

        // Parameterized constructor
        public WindInfo(double speed, int deg, double gust) {
            this.speed = speed;
            this.deg = deg;
            this.gust = gust;
        }

        // Getters and setters

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public double getGust() {
            return gust;
        }

        public void setGust(double gust) {
            this.gust = gust;
        }

        // toString method
        @Override
        public String toString() {
            return "WindInfo{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    ", gust=" + gust +
                    '}';
        }
    }

    // toString method
    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weatherText='" + weatherText + '\'' +
                ", hasPrecipitation=" + hasPrecipitation +
                ", precipitationType='" + precipitationType + '\'' +
                ", isDayTime=" + isDayTime +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}

