package goeuro.util;

import java.nio.charset.StandardCharsets;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoJSONParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoJSONParser.class);

    private JSONObject jsonCity;

    public GoJSONParser(JSONObject jsonCity) {
        this.jsonCity = jsonCity;

    }

    public String parse() {
        LOGGER.debug("JSON city: {}", jsonCity);
        
        String id = getIntString(jsonCity, GoConstants._ID);
        String name = getString(jsonCity, GoConstants.NAME);
        String type = getString(jsonCity, GoConstants.TYPE);

        JSONObject geo_position = jsonCity.getJSONObject(GoConstants.GEO_POSITION);
        String latitude = getDoubleString(geo_position, GoConstants.LATITUDE);
        String longitude = getDoubleString(geo_position, GoConstants.LONGITUDE);

        String line = createLine(id, name, type, latitude, longitude);
        return line;
    }

    private String getString(final JSONObject obj, final String key) {
        if (obj == null || obj.isEmpty()) {
            throw new IllegalArgumentException(GoConstants.JSON_NULL);
        }
        String resp = GoConstants.EMPTY;
        try {
            resp = obj.getString(key);
            resp = new String(resp.getBytes(), StandardCharsets.UTF_8);
        } catch (JSONException e) {
            LOGGER.debug("No key: {}, returning empty", key);
        }
        return resp;
    }

    private String getDoubleString(final JSONObject obj, final String key) {
        if (obj == null || obj.isEmpty()) {
            throw new IllegalArgumentException(GoConstants.JSON_NULL);
        }
        String resp = GoConstants.EMPTY;
        try {
            Double tmp = obj.getDouble(key);
            resp = tmp.toString();
        } catch (JSONException e) {
            LOGGER.debug("No key: {}, returning empty", key);
        }
        return resp;
    }

    private String getIntString(final JSONObject obj, final String key) {
        if (obj == null || obj.isEmpty()) {
            throw new IllegalArgumentException(GoConstants.JSON_NULL);
        }
        String resp = GoConstants.EMPTY;
        try {
            Integer tmp = obj.getInt(key);
            resp = tmp.toString();
        } catch (JSONException e) {
            LOGGER.debug("No key: {}, returning empty", key);
        }
        return resp;
    }

    private String createLine(String id, String name, String type, String latitude, String longitude) {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(GoConstants.SEPARATOR);
        sb.append(name).append(GoConstants.SEPARATOR);
        sb.append(type).append(GoConstants.SEPARATOR);
        sb.append(latitude).append(GoConstants.SEPARATOR);
        sb.append(longitude).append(System.lineSeparator());
        return sb.toString();
    }
}
