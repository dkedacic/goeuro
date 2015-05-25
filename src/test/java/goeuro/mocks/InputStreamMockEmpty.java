package goeuro.mocks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputStreamMockEmpty extends InputStream {

    public static final byte[] RESPONSE = "[]".getBytes(StandardCharsets.UTF_8);

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
            InputStreamMockEmpty mock = new InputStreamMockEmpty();

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
