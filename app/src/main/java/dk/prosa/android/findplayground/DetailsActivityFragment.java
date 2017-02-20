package dk.prosa.android.findplayground;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dk.prosa.android.findplayground.model.FeatureModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private FeatureModel mFeatureModel;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    public void setFeatureModel(FeatureModel featureModel){
        this.mFeatureModel = featureModel;
        ((TextView)getView().findViewById(R.id.detailsName)).setText(featureModel.getProperties().getName());

    }


}
