package goeuro.util;


public class Util {

    /**
     * In case of more complicated command line parameters I would use some other library to parse params.
     * 
     * @param args
     * @return
     */
    public static String parseCity(final String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(GoConstants.ONE_PARAM_MSG);
        }
        final String city = args[0].trim();
        if (city.isEmpty()) {
            throw new IllegalArgumentException(GoConstants.EMPTY_CITY_MSG);
        }

        return city;
    }

   

}
