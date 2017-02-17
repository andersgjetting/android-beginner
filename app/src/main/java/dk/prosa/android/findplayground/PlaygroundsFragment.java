package dk.prosa.android.findplayground;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playgrounds, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View root = getView();
        final RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.playgroundsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new PlaygroundsAdapter(getPlaygrounds()));

    }

    private List<String> getPlaygrounds(){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            list.add("Playground: " + i);
        }
        return list;
    }
}
