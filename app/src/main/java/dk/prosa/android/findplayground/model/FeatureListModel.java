package dk.prosa.android.findplayground.model;

import java.util.List;

/**
 * Created by andersgjetting on 18/02/2017.
 */

public class FeatureListModel {
    private int totalFeatures;

    private List<FeatureModel> features;

    public int getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(int totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public List<FeatureModel> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureModel> features) {
        this.features = features;
    }
}
