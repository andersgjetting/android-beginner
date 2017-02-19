package dk.prosa.android.findplayground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andersgjetting on 16/02/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureModel {

    private String id;
    private String type;
    private Geometry geometry;

    private FeatureProperties properties;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public FeatureProperties getProperties() {
        return properties;
    }

    public void setProperties(FeatureProperties properties) {
        this.properties = properties;
    }

    public static class Geometry{
        private String type;
        private List<Coordinate> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Coordinate> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Coordinate> coordinates) {
            this.coordinates = coordinates;
        }

        public Coordinate getLocationCoordinates(){
            if(coordinates != null && coordinates.size() > 0){
                return coordinates.get(0);
            }
            throw new IllegalStateException("Size of coordinates list not valid");
        }
    }


    public static class Coordinate extends ArrayList<Double> {

        public double getLatitude(){
            if(size() > 1){
                return get(1);
            }
            throw new IllegalStateException("coodinates not defined");
        }

        public double getLongitude(){
            if(size() > 1){
                return get(0);
            }
            throw new IllegalStateException("coodinates not defined");
        }

    }

    public static class FeatureProperties extends HashMap<String, String>{
        public String getName(){
            return get("navn");
        }

        public String getAgeGroups(){
            return get("aldersgruppe");
        }

    }
}
