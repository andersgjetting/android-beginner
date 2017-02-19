package dk.prosa.android.findplayground;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.prosa.android.findplayground.model.FeatureListLoader;
import dk.prosa.android.findplayground.model.FeatureListModel;
import dk.prosa.android.findplayground.model.FeatureModel;
import dk.prosa.android.findplayground.model.IPlaygroundListViewModel;
import dk.prosa.android.findplayground.model.IPlaygroundViewModel;

/**
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsFragment extends Fragment implements LoaderManager.LoaderCallbacks<FeatureListModel>{

    private Location mCurrentLocation;
    private RecyclerView mRecyclerView;
    private TextView mTotalCount;
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
    }

    private void setupUI(IPlaygroundListViewModel playgroundListViewModel){
        mTotalCount.setText(playgroundListViewModel.getTotalCount());
        mRecyclerView.setAdapter(new PlaygroundsAdapter(playgroundListViewModel.getPlaygroundModels()));
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




    class PlaygroundListViewModel implements IPlaygroundListViewModel{

        final FeatureListModel featureListModel;
        final List<IPlaygroundViewModel> playgroundViewModelList;

        PlaygroundListViewModel(FeatureListModel featureListModel) {
            this.featureListModel = featureListModel;
            playgroundViewModelList = new ArrayList<>();
            for(FeatureModel featureModel : featureListModel.getFeatures()){
                playgroundViewModelList.add(new PlaygroundViewModel(featureModel));
            }

        }

        @Override
        public String getTotalCount() {
            return getResources().getString(R.string.playgrounds_total_count_label, featureListModel.getTotalFeatures());
        }

        @Override
        public List<IPlaygroundViewModel> getPlaygroundModels() {
            return playgroundViewModelList;
        }
    }

    class PlaygroundViewModel implements IPlaygroundViewModel{

        final FeatureModel featureModel;

        PlaygroundViewModel(FeatureModel featureModel) {
            this.featureModel = featureModel;
        }


        @Override
        public String getName() {
            return featureModel.getProperties().getName();
        }

        @Override
        public String getDistance() {
            String distance = getResources().getString(R.string.feature_distance_unknown_label);
            if(mCurrentLocation != null) {

                try {
                    FeatureModel.Coordinate locationCoordinate = featureModel.getGeometry().getLocationCoordinates();
                    double latitude = locationCoordinate.getLatitude();
                    double longitude = locationCoordinate.getLongitude();
                    float distanceResult[] = new float[1];
                    Location.distanceBetween(latitude, longitude, mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), distanceResult);

                    distance = String.valueOf(distanceResult[0]);
                }catch(Exception ex){
                    Log.w("PlaygroundsFragment", "No valid coordinates found");
                }
            }

            return getResources().getString(R.string.feature_distance_label, distance);
        }

        @Override
        public String getAgeGroup() {
            return getResources().getString(R.string.feature_age_group_label, featureModel.getProperties().getAgeGroups());
        }
    }


}
