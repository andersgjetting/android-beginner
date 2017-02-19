package dk.prosa.android.findplayground;


import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String DATA_CURRENT_FRAGMENT = "DATA_CURRENT_FRAGMENT";
    private static final int FRAGMENT_WELCOME = 1;
    private static final int FRAGMENT_PLAYGROUND = 2;

    private DrawerLayout mDrawerLayout;
    private int mCurrentFragment = FRAGMENT_WELCOME;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            mCurrentFragment = savedInstanceState.getInt(DATA_CURRENT_FRAGMENT, FRAGMENT_WELCOME);
        }

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        setupFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DATA_CURRENT_FRAGMENT, mCurrentFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupFragment(){
        Fragment newFragment;
        switch(mCurrentFragment){
            case FRAGMENT_PLAYGROUND:
                newFragment = new PlaygroundsFragment();
                break;
            default:
                newFragment = new WelcomeFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.dataContainer, newFragment, "TAG_FRAGMENT").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


    }

    private void showPlaygroundsFragment(){
        mCurrentFragment = FRAGMENT_PLAYGROUND;
        setupFragment();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.nav_playgrounds:
                                showPlaygroundsFragment();
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
