package goeuro;

import goeuro.exception.NoCityInfoException;
import goeuro.http.HTTPExecutor;
import goeuro.mocks.InputStreamMock;
import goeuro.mocks.InputStreamMockEmpty;
import goeuro.util.GoConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HTTPExecutorTest {

    private static final String CITY = "Berlin";

    @Mock
    HttpURLConnection connection;

    @Mock
    InputStream inputStream;

    @Test
    public void executeRequest_201Code() throws IOException, NoCityInfoException {

        HTTPExecutor http = Mockito.spy(new HTTPExecutor());

        Mockito.doReturn(connection).when(http).openConnection();
        Mockito.doReturn(201).when(connection).getResponseCode();

        http.setCity(CITY);
        http.setUrl(GoConstants.ENDPOINT);

        JSONArray response = http.executeRequest();
        assertTrue("Expected empty array!", response.isEmpty());

    }

    @Test
    public void executeRequest_200Code() throws IOException, NoCityInfoException {

        HTTPExecutor http = Mockito.spy(new HTTPExecutor());

        Mockito.doReturn(connection).when(http).openConnection();
        Mockito.doReturn(new InputStreamMock()).when(connection).getInputStream();
        Mockito.doReturn(200).when(connection).getResponseCode();

        http.setCity(CITY);
        http.setUrl(GoConstants.ENDPOINT);

        JSONArray response = http.executeRequest();
        assertEquals("Expected equal arrays", InputStreamMock.RESPONSE.length, response.toString().getBytes(StandardCharsets.UTF_8).length);

    }
    
    @Test(expected = NoCityInfoException.class)
    public void executeRequest_200CodeEmpty() throws IOException, NoCityInfoException {

        HTTPExecutor http = Mockito.spy(new HTTPExecutor());

        Mockito.doReturn(connection).when(http).openConnection();
        Mockito.doReturn(new InputStreamMockEmpty()).when(connection).getInputStream();
        Mockito.doReturn(200).when(connection).getResponseCode();

        http.setCity(CITY);
        http.setUrl(GoConstants.ENDPOINT);

        http.executeRequest();

    }

}
