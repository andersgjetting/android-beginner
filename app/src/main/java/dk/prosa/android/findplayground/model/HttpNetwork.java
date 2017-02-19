package dk.prosa.android.findplayground.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class HttpNetwork {

    final static ObjectMapper mapper = new ObjectMapper();

    public static <T> T httpGet(String urlString, final Class<T> responseClass) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return mapper.readValue(in, responseClass);
        } finally {
            urlConnection.disconnect();
        }
    }

}
