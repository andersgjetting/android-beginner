package dk.prosa.android.findplayground;

import java.util.HashMap;

/**
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundList extends FeatureList<PlaygroundList.PlaygroundProperties> {


    public static class PlaygroundProperties extends HashMap<String, String> implements Feature.FeatureProperties{

        private static final String KEY_NAME = "navn";
        private static final String KEY_DESCRIPTION = "beskrivelse";


        @Override
        public String getName() {
            return get(KEY_NAME);
        }

        @Override
        public String getDescription() {
            return get(KEY_DESCRIPTION);
        }
    }


}
