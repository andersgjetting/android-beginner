package dk.prosa.android.findplayground;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.OperationCanceledException;

import java.io.IOException;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class FeatureListLoader<T> extends AsyncTaskLoader<T> {

    final Class<T> responseClass;
    public FeatureListLoader(Context context, Class<T> responseClass) {
        super(context);
        this.responseClass = responseClass;
    }

    @Override
    public T loadInBackground() throws OperationCanceledException {

        try {
            return HttpNetwork.httpGet("http://wfs-kbhkort.kk.dk/k101/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=k101:legeplads&outputFormat=json&SRSNAME=EPSG:4326", responseClass);
        } catch (IOException e) {
            return null;
        }
    }
}
