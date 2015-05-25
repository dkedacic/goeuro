package goeuro;

import goeuro.exception.NoCityInfoException;
import goeuro.file.CSVFileWriter;
import goeuro.file.IFileWriter;
import goeuro.http.HTTPExecutor;
import goeuro.util.GoConstants;
import goeuro.util.Util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoEuro {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoEuro.class);

    private HTTPExecutor httpExecutor;

    private IFileWriter fileWriter;

    public static void main(String[] args) {
        try {
            GoEuro go = new GoEuro();
            go.setHttpExecutor(new HTTPExecutor());
            go.setFileWriter(new CSVFileWriter());

            go.retrieveCityInfo(args);
        } catch (NoCityInfoException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Communication error. Unable to retrieve city information: ", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Argument error: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve city information: ", e);
        }
    }

    public void retrieveCityInfo(String[] args) throws IOException, NoCityInfoException {
        String city = Util.parseCity(args);

        httpExecutor.setCity(city);
        httpExecutor.setUrl(GoConstants.ENDPOINT);
        fileWriter.writeToFile(city, httpExecutor.executeRequest());
    }

    public void setHttpExecutor(final HTTPExecutor httpExecutor) {
        this.httpExecutor = httpExecutor;
    }

    public void setFileWriter(final IFileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

}
