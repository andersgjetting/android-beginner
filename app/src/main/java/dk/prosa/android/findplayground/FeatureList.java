package dk.prosa.android.findplayground;

import java.util.List;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class FeatureList<T extends Feature.FeatureProperties> {

    private int totalFeatures;
    private String type;

    private List<Feature<T>> features;

    public int getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(int totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature<T>> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature<T>> features) {
        this.features = features;
    }
}
