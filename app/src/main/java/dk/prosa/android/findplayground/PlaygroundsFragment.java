package dk.prosa.android.findplayground;


import android.os.Bundle;
import android.support.v4.content.Loader;

import dk.prosa.android.findplayground.model.PlaygroundListLoader;
import dk.prosa.android.findplayground.model.PlaygroundListModel;

/**
 *
 *  test lat lon
 *  lon: 12.562375
 *  latitude: 55.679089
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsFragment extends AbstractFeatureFragment<PlaygroundListModel>{




    @Override
    public Loader<PlaygroundListModel> onCreateLoader(int id, Bundle args) {
        return new PlaygroundListLoader(getActivity());
    }


    @Override
    String getSecondaryLabel(String secondaryValue) {
        return getResources().getString(R.string.feature_age_group_label, secondaryValue);
    }
}
