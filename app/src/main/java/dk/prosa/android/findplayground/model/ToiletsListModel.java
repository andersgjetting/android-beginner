package dk.prosa.android.findplayground.model;

/**
 * Created by andersgjetting on 27/02/2017.
 */

public class ToiletsListModel extends FeatureListModel<ToiletsListModel.ToiletProperties> {

    public static class ToiletProperties extends FeatureModel.FeatureProperties{

        @Override
        public String getSecondaryLabel() {
            return get("handicapadgang");
        }

        @Override
        public String getName() {
            return get("adresse");
        }
    }
}
