package dk.prosa.android.findplayground;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.prosa.android.findplayground.model.IPlaygroundListViewModel;
import dk.prosa.android.findplayground.model.IPlaygroundViewModel;

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

        final TextView totalCount = (TextView) root.findViewById(R.id.totalCount);

        final IPlaygroundListViewModel playgroundListViewModel = getPlaygroundListViewModel();
        totalCount.setText(playgroundListViewModel.getTotalCount());

        final RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.playgroundsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new PlaygroundsAdapter(playgroundListViewModel.getPlaygroundModels()));

    }

    private IPlaygroundListViewModel getPlaygroundListViewModel(){
        return new IPlaygroundListViewModel() {
            @Override
            public String getTotalCount() {
                return getResources().getString(R.string.playgrounds_total_count_label);
            }

            @Override
            public List<IPlaygroundViewModel> getPlaygroundModels() {
                return getPlaygrounds();
            }
        };
    }

    private List<IPlaygroundViewModel> getPlaygrounds(){
        List<IPlaygroundViewModel> list = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            final int count = i;
            list.add(new IPlaygroundViewModel() {
                @Override
                public String getName() {
                    return "playground: " + count;
                }

                @Override
                public String getDistance() {
                    return "Afstand: " + count;
                }

                @Override
                public String getAgeGroup() {
                    return "Alder: " + count;
                }
            });
        }
        return list;
    }
}
