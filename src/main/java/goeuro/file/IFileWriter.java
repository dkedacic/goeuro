package goeuro.file;

import java.io.IOException;

import net.sf.json.JSONArray;

public interface IFileWriter {

    void writeToFile(String city, JSONArray response) throws IOException;

}
