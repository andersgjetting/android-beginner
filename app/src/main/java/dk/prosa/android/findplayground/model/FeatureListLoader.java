package dk.prosa.android.findplayground.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class FeatureListLoader {



    public FeatureListModel getFeatureListModel(){
        FeatureListModel featureListModel = new FeatureListModel();
        featureListModel.setTotalFeatures(98);
        featureListModel.setFeatures(generateFeatureModelList());
        return featureListModel;
    }

    private List<FeatureModel> generateFeatureModelList(){
        List<FeatureModel> list = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            final int count = i;

            FeatureModel featureModel = new FeatureModel();

            FeatureModel.FeatureProperties properties = new FeatureModel.FeatureProperties();
            properties.put("navn", "playground: " + count);
            properties.put("aldersgruppe", "Alder: " + count);

            featureModel.setProperties(properties);


            list.add(featureModel);
        }
        return list;
    }


}
