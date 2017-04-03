package dk.prosa.android.findplayground;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dk.prosa.android.findplayground.model.FeatureListLoader;
import dk.prosa.android.findplayground.model.FeatureListModel;
import dk.prosa.android.findplayground.model.FeatureModel;
import dk.prosa.android.findplayground.model.IPlaygroundListViewModel;
import dk.prosa.android.findplayground.model.IPlaygroundViewModel;

/**
 *
 *  test lat lon
 *  lon: 12.562375
 *  latitude: 55.679089
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsFragment extends Fragment implements LoaderManager.LoaderCallbacks<FeatureListModel>, LocationListener{

    private RecyclerView mRecyclerView;
    private PlaygroundsAdapter mPlaygroundsAdapter;
    private TextView mTotalCount;
    private PlaygroundListViewModel mPlaygroundListViewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playgrounds, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View root = getView();
        mTotalCount = (TextView) root.findViewById(R.id.totalCount);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.playgroundsRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getLoaderManager().initLoader(0, null, this).forceLoad();
        Log.d("minlogger", "jeg logger data ud");
    }

    @Override
    public void onResume() {
        super.onResume();
        startLocationManager();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationManager();

    }

    private void startLocationManager(){
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }



    }

    private void stopLocationManager(){
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.removeUpdates(this);
        }


    }



    private void setupUI(PlaygroundListViewModel playgroundListViewModel){
        mPlaygroundListViewModel = playgroundListViewModel;
        mTotalCount.setText(playgroundListViewModel.getTotalCount());
        mRecyclerView.setAdapter(mPlaygroundsAdapter = new PlaygroundsAdapter(mPlaygroundListViewModel.getPlaygroundModels()));

        Location location = new Location("my provider");
        location.setLatitude(55.679089);
        location.setLongitude(12.562375);
        onLocationChanged(location);
    }


    @Override
    public Loader<FeatureListModel> onCreateLoader(int id, Bundle args) {
        return new FeatureListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<FeatureListModel> loader, FeatureListModel data) {
        setupUI(new PlaygroundListViewModel(data));
    }

    @Override
    public void onLoaderReset(Loader<FeatureListModel> loader) {

    }

    //LocationListener start
    @Override
    public void onLocationChanged(Location location) {
        if(mPlaygroundsAdapter != null){
            mPlaygroundListViewModel.onLocationChanged(location);
            mPlaygroundsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    //LocationListener end


    class PlaygroundListViewModel implements IPlaygroundListViewModel{

        final FeatureListModel featureListModel;
        final List<PlaygroundViewModel> playgroundViewModelList;

        PlaygroundListViewModel(FeatureListModel featureListModel) {
            this.featureListModel = featureListModel;
            playgroundViewModelList = new ArrayList<>();
            for(FeatureModel featureModel : featureListModel.getFeatures()){
                playgroundViewModelList.add(new PlaygroundViewModel(featureModel));
            }

        }

        public void onLocationChanged(Location newLocation){
            if(playgroundViewModelList == null){
                return;
            }

            for(PlaygroundViewModel playgroundViewModel : playgroundViewModelList){
                playgroundViewModel.onLocationChanged(newLocation);
            }
            Collections.sort(playgroundViewModelList, new Comparator<PlaygroundViewModel>() {
                @Override
                public int compare(PlaygroundViewModel o1, PlaygroundViewModel o2) {
                    if(o1 == null || o2 == null){
                        return 0;
                    }
                    if(o1.distanceInMeters > o2.distanceInMeters){
                        return 1;
                    }

                    if(o1.distanceInMeters < o2.distanceInMeters){
                        return -1;
                    }
                    return 0;
                }
            });
        }

        @Override
        public String getTotalCount() {
            return getResources().getString(R.string.playgrounds_total_count_label, featureListModel.getTotalFeatures());
        }

        @Override
        public List<PlaygroundViewModel> getPlaygroundModels() {
            return playgroundViewModelList;
        }
    }

    class PlaygroundViewModel implements IPlaygroundViewModel{

        private final static float DISTANCE_UNDEFINED = -1f;
        final FeatureModel featureModel;

        private float distanceInMeters = DISTANCE_UNDEFINED;

        PlaygroundViewModel(FeatureModel featureModel) {
            this.featureModel = featureModel;
        }


        @Override
        public String getName() {
            return featureModel.getProperties().getName();
        }

        public void onLocationChanged(Location newLocation){
            distanceInMeters = DISTANCE_UNDEFINED;
            if(newLocation != null) {

                try {
                    FeatureModel.Coordinate locationCoordinate = featureModel.getGeometry().getLocationCoordinates();
                    double latitude = locationCoordinate.getLatitude();
                    double longitude = locationCoordinate.getLongitude();
                    float distanceResult[] = new float[1];
                    Location.distanceBetween(latitude, longitude, newLocation.getLatitude(), newLocation.getLongitude(), distanceResult);

                    if(distanceResult[0] < 1.f){
                        distanceInMeters = 0.f;
                    }else{
                        distanceInMeters = Math.round(distanceResult[0]) / 1000.f;
                    }

                }catch(Exception ex){
                    Log.w("PlaygroundsFragment", "No valid coordinates found");
                }
            }
        }


        @Override
        public String getDistance() {
            String distance = (distanceInMeters != DISTANCE_UNDEFINED) ? String.valueOf(distanceInMeters) : getResources().getString(R.string.feature_distance_unknown_label);
            return getResources().getString(R.string.feature_distance_label, distance);
        }

        @Override
        public String getAgeGroup() {
            return getResources().getString(R.string.feature_age_group_label, featureModel.getProperties().getAgeGroups());
        }
    }


}
