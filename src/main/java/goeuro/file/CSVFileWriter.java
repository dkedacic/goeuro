package goeuro.file;

import goeuro.util.GoJSONParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVFileWriter implements IFileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileWriter.class);
    private static final String FILE_EXTENSION = ".csv";

    @Override
    public void writeToFile(String city, JSONArray response) throws IOException {
        if (!response.isEmpty()) {
            String filename = city + FILE_EXTENSION;
            try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonCity = response.getJSONObject(i);
                    
                    GoJSONParser citiParser = new GoJSONParser(jsonCity);
                    String line = citiParser.parse();
                    out.write(line);
                }
                LOGGER.info("File created: {}", filename);
            }
        }
    }



}
