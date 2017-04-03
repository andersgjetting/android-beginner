package dk.prosa.android.findplayground.model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class FeatureListLoader extends AsyncTaskLoader<FeatureListModel> {


    public FeatureListLoader(Context context) {
        super(context);
    }

    @Override
    public FeatureListModel loadInBackground() {
        try {
            return HttpNetwork.httpGet("http://wfs-kbhkort.kk.dk/k101/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=k101:legeplads&outputFormat=json&SRSNAME=EPSG:4326",
                    FeatureListModel.class);
        } catch (Exception e) {
            Log.e("FeatureListLoader", "error loading data", e);
            return null;
        }
    }
}
