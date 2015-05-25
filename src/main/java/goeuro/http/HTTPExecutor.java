package goeuro.http;

import goeuro.exception.NoCityInfoException;
import goeuro.util.GoConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPExecutor.class);
    private String city;
    private URL url = null;

    public HTTPExecutor() {
    }

    public HTTPExecutor(final String city) {
        this.city = city;
    }

    public JSONArray executeRequest() throws IOException, NoCityInfoException {
        StringBuffer httpResponse = new StringBuffer(GoConstants._2048);

        HttpURLConnection con = null;
        try {
            con = openConnection();
            con.setRequestMethod(GoConstants.GET);

            int responseCode = -1;
            responseCode = con.getResponseCode();
            if (responseCode == GoConstants.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        httpResponse.append(inputLine);
                    }
                }
            } else {
                LOGGER.error("Unexpected response code returned: {}", responseCode);
                return new JSONArray();
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        JSONArray response = new JSONArray(httpResponse.toString());
        if (response.isEmpty()) {
            throw new NoCityInfoException("No information for city: " + getCity());
        }
        LOGGER.debug("Response: {}", response);
        return response;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        if (city == null) {
            throw new IllegalArgumentException(GoConstants.CITY_NOT_SET);
        }
        return city;
    }
    
    public HttpURLConnection openConnection() throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
    
    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url + getCity());
    }
}
