package goeuro;

import static org.junit.Assert.assertEquals;
import goeuro.exception.NoCityInfoException;
import goeuro.file.IFileWriter;
import goeuro.http.HTTPExecutor;
import goeuro.util.GoConstants;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GoEuroTest {

    @Mock
    HTTPExecutor httpExecutor;

    @Mock
    IFileWriter fileWriter;

    @Test
    public void testRetrieveCityInfo_NoParams() {
        GoEuro go = new GoEuro();
        try {
            go.retrieveCityInfo(new String[] {});
        } catch (IOException | NoCityInfoException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            assertEquals("Paremeters empty!", GoConstants.ONE_PARAM_MSG, e.getMessage());
        }
    }

    @Test
    public void testRetrieveCityInfo_CityEmpty() {
        GoEuro go = new GoEuro();
        try {
            go.retrieveCityInfo(new String[] { "" });
        } catch (IOException | NoCityInfoException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            assertEquals("Paremeters empty!", GoConstants.EMPTY_CITY_MSG, e.getMessage());
        }
    }

    @Test
    public void testRetrieveCityInfo_2Cities() {
        GoEuro go = new GoEuro();
        try {
            go.retrieveCityInfo(new String[] { "Berlin", "Athlone" });
        } catch (IOException | NoCityInfoException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            assertEquals("More than 1 parameter specified!", GoConstants.ONE_PARAM_MSG, e.getMessage());
        }
    }

    @Test
    public void testRetrieveCityInfo() {
        GoEuro go = new GoEuro();
        go.setHttpExecutor(httpExecutor);
        go.setFileWriter(fileWriter);
        try {
            go.retrieveCityInfo(new String[] { "Berlin" });
        } catch (IOException | NoCityInfoException e) {
            e.printStackTrace();
        }
    }

}
