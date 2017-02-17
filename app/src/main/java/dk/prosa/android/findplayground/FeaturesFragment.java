package dk.prosa.android.findplayground;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class FeaturesFragment extends Fragment implements LoaderManager.LoaderCallbacks<PlaygroundList> {


    @Override
    public Loader<PlaygroundList> onCreateLoader(int id, Bundle args) {
        return new FeatureListLoader<>(getActivity(), PlaygroundList.class);
    }

    @Override
    public void onLoadFinished(Loader<PlaygroundList> loader, PlaygroundList data) {

    }

    @Override
    public void onLoaderReset(Loader<PlaygroundList> loader) {

    }
}
