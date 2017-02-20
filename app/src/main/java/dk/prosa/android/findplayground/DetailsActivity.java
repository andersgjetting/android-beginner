package dk.prosa.android.findplayground;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dk.prosa.android.findplayground.model.FeatureModel;

public class DetailsActivity extends AppCompatActivity {
    private static final String DATA_FEATURE_MODEL = "DATA_FEATURE_MODEL";

    public static Intent getLauncherIntent(Context context, FeatureModel featureModel){
        Intent i = new Intent(context, DetailsActivity.class);
        i.putExtra(DATA_FEATURE_MODEL, featureModel);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final FeatureModel featureModel = (FeatureModel)getIntent().getExtras().getSerializable(DATA_FEATURE_MODEL);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FeatureModel.Coordinate coordinate = featureModel.getGeometry().getCoordinates().get(0);
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+coordinate.getLatitude()+"," + coordinate.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();

        DetailsActivityFragment fragment = (DetailsActivityFragment)fm.findFragmentById(R.id.fragment);
        fragment.setFeatureModel(featureModel);

    }

}
