package com.example.kb.clearsky.location;

import android.location.Location;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import okhttp3.HttpUrl;

/**
 * Created by Karol on 2017-08-10.
 */

public class LocationImageUrlBuilder {

    public static final int ZOOM_WORLD = 1;
    public static final int ZOOM_CONTINENT = 5;
    public static final int ZOOM_CITY = 10;
    public static final int ZOOM_CITY_PLUS = 12;
    public static final int ZOOM_STREETS = 15;
    public static final int ZOOM_BUILDINGS = 20;
    public static final String COLOR_BLUE = "0x001faa";
    public static final String COLOR_GREEN = "0x3EF177";
    public static final String MAPTYPE_ROADMAP = "roadmap";
    public static final String MAPTYPE_SATELLITE = "satellite";
    public static final String MAPTYPE_HYBRID = "hybrid";
    public static final String MAPTYPE_TERRAIN = "terrain";
    public static final String MARKER_TINY = "tiny";
    public static final String MARKER_MID = "mid";
    public static final String MARKER_SMALL = "small";

    private static final String PIPE = "%7C"; // sing of '|'
    private static final String COLON = "%3A"; // sing of ':'
    private static final String SLASH = "/"; // sing of '/'

    private static final String SCHEME_HTTPS = "https";
    private static final String GOOGLE_MAPS_HOST = "maps.googleapis.com";
    private static final String GOOGLE_PATH = "maps" + SLASH + "api" + SLASH + "staticmap";

    // note that u must generate your own GoogleApi key, that one will be disabled
    private static final String KEY_STATIC_MAPS = "AIzaSyBK1-ju7NlNrMK7P3wV2LYbhgIVS5ft2os";

    private static final String QUERY_ZOOM = "zoom";
    private static final String QUERY_LOCATION = "center";
    private static final String QUERY_MARKER = "markers";
    private static final String QUERY_MAPTYPE = "maptype";
    private static final String QUERY_KEY = "key";
    private static final String MARKER_COLOR = "color";
    private static final String MARKER_SIZE = "size";
    private static final String QUERY_SIZE = "size";

    private static final String ENCODING_UTF_8 = "UTF-8";

    /**
     * private constructor to not instantiate the class
     */
    private LocationImageUrlBuilder() {
    }

    public static URL buildUrl(Location location, String mapType, int zoom, String markerColor, String markerSize, String size) throws UnsupportedEncodingException, MalformedURLException {
        HttpUrl url = new HttpUrl.Builder().scheme(SCHEME_HTTPS)
                .host(GOOGLE_MAPS_HOST)
                .addPathSegment(GOOGLE_PATH)
                .addQueryParameter(QUERY_ZOOM, Integer.toString(zoom))
                .addQueryParameter(QUERY_MAPTYPE, mapType)
                .addQueryParameter(QUERY_LOCATION, parse(location))
                .addQueryParameter(QUERY_MARKER, parseMarkersParams(new String[]{MARKER_COLOR, MARKER_SIZE},
                        new String[]{markerColor, markerSize}, location))
                .addQueryParameter(QUERY_KEY, KEY_STATIC_MAPS)
                .addQueryParameter(QUERY_SIZE, size) //TODO thats temporary need fixing
                .build();
        URL decoded = new URL(URLDecoder.decode(url.toString(), "UTF-8"));
        return decoded;
    }

    public static String parseSizeToString(int width, int height) {
        return width + "x" + height;
    }

    private static String parse(Location location) {
        return location.getLatitude() + "," + location.getLongitude();
    }

    private static String parseMarkersParams(String[] keys, String[] values, Location location) {
        StringBuilder sb = null;
        if (keys != null && values != null && keys.length == values.length) {
            sb = new StringBuilder();
            for (int i = 0; i < keys.length; i++) {
                if (i > 0) {
                    sb.append(PIPE);
                }
                sb.append(keys[i] + COLON + values[i]);
            }
            if (location != null) {
                sb.append(PIPE);
                sb.append(location.getLatitude() + "," + location.getLongitude());
            }
        }
        String result = sb != null ? sb.toString() : "";
        return result;
    }

}
