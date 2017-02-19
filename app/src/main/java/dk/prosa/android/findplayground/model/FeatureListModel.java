package dk.prosa.android.findplayground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by andersgjetting on 18/02/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
