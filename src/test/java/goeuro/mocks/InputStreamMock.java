package goeuro.mocks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputStreamMock extends InputStream {

    public static final byte[] RESPONSE = "[{'coreCountry':true,'geo_position':{'longitude':13.41053,'latitude':52.52437},'distance':null,'_id':376217,'inEurope':true,'name':'Berlin','countryCode':'DE','locationId':8384,'iata_airport_code':null,'fullName':'Berlin, Germany','type':'location','key':null,'country':'Germany'},{'coreCountry':true,'geo_position':{'longitude':10.04366,'latitude':45.50298},'distance':null,'_id':448103,'inEurope':true,'name':'Berlingo','countryCode':'IT','locationId':147721,'iata_airport_code':null,'fullName':'Berlingo, Italy','type':'location','key':null,'country':'Italy'},{'coreCountry':true,'geo_position':{'longitude':10.2384,'latitude':51.45775},'distance':null,'_id':425332,'inEurope':true,'name':'Berlingerode','countryCode':'DE','locationId':124675,'iata_airport_code':null,'fullName':'Berlingerode, Germany','type':'location','key':null,'country':'Germany'},{'coreCountry':true,'geo_position':{'longitude':13.58708,'latitude':52.67982},'distance':null,'_id':425326,'inEurope':true,'name':'Bernau bei Berlin','countryCode':'DE','locationId':124669,'iata_airport_code':null,'fullName':'Bernau bei Berlin, Germany','type':'location','key':null,'country':'Germany'},{'coreCountry':true,'geo_position':{'longitude':13.28903,'latitude':52.5548},'distance':null,'_id':314826,'inEurope':true,'name':'Berlin Tegel','countryCode':'DE','locationId':null,'iata_airport_code':'TXL','fullName':'Berlin Tegel (TXL), Germany','type':'airport','key':null,'country':'Germany'},{'coreCountry':true,'geo_position':{'longitude':13.5180874,'latitude':52.3887261},'distance':null,'_id':314827,'inEurope':true,'name':'Berlin Schoenefeld','countryCode':'DE','locationId':null,'iata_airport_code':'SXF','fullName':'Berlin Schoenefeld (SXF), Germany','type':'airport','key':null,'country':'Germany'}]"
            .getBytes(StandardCharsets.UTF_8);

    private int position = -1;

    @Override
    public int read() throws IOException {
        position++;
        if (position >= RESPONSE.length) {
            return -1;
        }
        return (int) RESPONSE[position];
    }

    public static void main(String[] args) {
        try {
            InputStreamMock mock = new InputStreamMock();

            while (true) {
                int ch = mock.read();
                if (ch != -1) {
                    System.out.println((char) ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
