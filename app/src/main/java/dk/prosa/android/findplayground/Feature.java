package dk.prosa.android.findplayground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andersgjetting on 16/02/2017.
 */

public class Feature<T extends Feature.FeatureProperties> {

    private String id;
    private String type;
    private Geometry geometry;

    T properties;



    public static class Geometry{
        private String type;
        private List<Coordinate> coordinates;



    }
    public static class Coordinate extends ArrayList<Double> {

        public double getLatitude(){
            if(size() > 1){
                return get(0);
            }
            throw new IllegalStateException("coodinates not defined");
        }

        public double getLongitude(){
            if(size() > 1){
                return get(1);
            }
            throw new IllegalStateException("coodinates not defined");
        }

    }

    interface FeatureProperties{
        String getName();
        String getDescription();

    }
}
