package goeuro.util;

public interface GoConstants {

    int _2048 = 2048;
    String ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";
    String GET = "GET";
    String ONE_PARAM_MSG = "Parameter must be exactly one city name, example: \n java -jar GoEuroTest.jar \"CITY_NAME\"";
    String EMPTY_CITY_MSG = "There must be exactly one parameter - City name. Must not be empty!";
    String CITY_NOT_SET ="City parameter is null.";
    
    //json keys
    String _ID = "_id";
    String NAME = "name"; 
    String TYPE = "type"; 
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
    String GEO_POSITION = "geo_position";
    
    String EMPTY = "-";
    String JSON_NULL = "JSONObject parameter is null";
    
    String SEPARATOR = ",";
    
    int HTTP_OK = 200;
    
    
}
