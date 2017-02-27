package dk.prosa.android.findplayground;

import android.os.Bundle;
import android.support.v4.content.Loader;

import dk.prosa.android.findplayground.model.ToiletListLoader;
import dk.prosa.android.findplayground.model.ToiletsListModel;

/**
 * Created by andersgjetting on 27/02/2017.
 */

public class ToiletsFragment extends AbstractFeatureFragment<ToiletsListModel> {
    @Override
    public Loader<ToiletsListModel> onCreateLoader(int id, Bundle args) {
        return new ToiletListLoader(getContext());
    }


    @Override
    String getSecondaryLabel(String secondaryValue) {
        return getResources().getString(R.string.toilets_seconday_label, secondaryValue);
    }

    @Override
    String getTotalCountLabel(int count) {
        return getResources().getString(R.string.toilets_total_count_label, count);
    }
}
