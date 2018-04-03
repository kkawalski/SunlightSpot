package by.bsuir.kotiki.sunlightspot.model.icon;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

public final class IconStorage {
    private static final IconStorage instance = new IconStorage();
    private final Map<Integer, String> icons = new HashMap<>();

    private IconStorage() {
        icons.put(200, "storm-showers");
        icons.put(201, "storm-showers");
        icons.put(202, "storm-showers");
        icons.put(210, "storm-showers");
        icons.put(211, "thunderstorm");
        icons.put(212, "thunderstorm");
        icons.put(221, "thunderstorm");
        icons.put(230, "storm-showers");
        icons.put(231, "storm-showers");
        icons.put(232, "storm-showers");
        icons.put(300, "sprinkle");
        icons.put(301, "sprinkle");
        icons.put(302, "sprinkle");
        icons.put(310, "sprinkle");
        icons.put(311, "sprinkle");
        icons.put(312, "sprinkle");
        icons.put(313, "sprinkle");
        icons.put(314, "sprinkle");
        icons.put(321, "sprinkle");
        icons.put(500, "rain");
        icons.put(501, "rain");
        icons.put(502, "rain");
        icons.put(503, "rain");
        icons.put(504, "rain");
        icons.put(511, "rain-mix");
        icons.put(520, "showers");
        icons.put(521, "showers");
        icons.put(522, "showers");
        icons.put(531, "showers");
        icons.put(600, "snow");
        icons.put(601, "snow");
        icons.put(602, "snow");
        icons.put(611, "sleet");
        icons.put(612, "sleet");
        icons.put(615, "rain-mix");
        icons.put(616, "rain-mix");
        icons.put(620, "rain-mix");
        icons.put(621, "rain-mix");
        icons.put(622, "rain-mix");
        icons.put(701, "sprinkle");
        icons.put(711, "smoke");
        icons.put(721, "day-haze");
        icons.put(731, "cloudy-gusts");
        icons.put(741, "fog");
        icons.put(751, "cloudy-gusts");
        icons.put(761, "dust");
        icons.put(762, "smog");
        icons.put(771, "day-windy");
        icons.put(781, "tornado");
        icons.put(800, "sunny");
        icons.put(801, "cloudy");
        icons.put(802, "cloudy");
        icons.put(803, "cloudy");
        icons.put(804, "cloudy");
        icons.put(900, "tornado");
        icons.put(901, "hurricane");
        icons.put(902, "hurricane");
        icons.put(903, "snowflake-cold");
        icons.put(904, "hot");
        icons.put(905, "windy");
        icons.put(906, "hail");
        icons.put(951, "sunny");
        icons.put(952, "cloudy-gusts");
        icons.put(953, "cloudy-gusts");
        icons.put(954, "cloudy-gusts");
        icons.put(955, "cloudy-gusts");
        icons.put(956, "cloudy-gusts");
        icons.put(957, "cloudy-gusts");
        icons.put(958, "cloudy-gusts");
        icons.put(959, "cloudy-gusts");
        icons.put(960, "thunderstorm");
        icons.put(961, "thunderstorm");
        icons.put(962, "cloudy-gusts");
    }

    public static IconStorage getInstance() {
        return instance;
    }

    public Drawable getIcon(int code, Context context) {
        String path = icons.get(code);

        if (!(code > 699 && code < 800) && !(code > 899 && code < 1000)) {
            path = "day_" + path;
        }

        path = "wi_" + path;

        return context.getResources().getDrawable(context.getResources().getIdentifier(path, "drawable", context.getPackageName()));
    }
}
