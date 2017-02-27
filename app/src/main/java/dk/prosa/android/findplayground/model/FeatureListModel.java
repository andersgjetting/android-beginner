package dk.prosa.android.findplayground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by andersgjetting on 18/02/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureListModel<T extends FeatureModel.FeatureProperties> {
    private int totalFeatures;

    private List<FeatureModel<T>> features;

    public int getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(int totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public List<FeatureModel<T>> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureModel<T>> features) {
        this.features = features;
    }
}
