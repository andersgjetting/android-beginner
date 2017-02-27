package dk.prosa.android.findplayground.model;

/**
 * Created by andersgjetting on 27/02/2017.
 */

public class PlaygroundListModel extends FeatureListModel<PlaygroundListModel.PlaygroundProperties> {

    public static class PlaygroundProperties extends FeatureModel.FeatureProperties{

        @Override
        public String getSecondaryLabel() {
            return get("aldersgruppe");
        }

        @Override
        public String getName() {
            return get("navn");
        }
    }
}
