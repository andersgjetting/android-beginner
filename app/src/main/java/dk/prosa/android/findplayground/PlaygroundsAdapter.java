package dk.prosa.android.findplayground;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dk.prosa.android.findplayground.model.IPlaygroundViewModel;

/**
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsAdapter extends RecyclerView.Adapter<PlaygroundsAdapter.ViewHolder>{

    public interface Callback{
        void onPlaygroundSelected(int position);
    }

    final List<? extends IPlaygroundViewModel> originalData;
    final Callback callback;

    public PlaygroundsAdapter(List<? extends IPlaygroundViewModel> originalData, Callback callback) {
        this.originalData = originalData;
        this.callback = callback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_playground, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        IPlaygroundViewModel playgroundViewModel = originalData.get(position);
        holder.name.setText(playgroundViewModel.getName());
        holder.ageGroup.setText(playgroundViewModel.getAgeGroup());
        holder.distance.setText(playgroundViewModel.getDistance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onPlaygroundSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return originalData == null ? 0 : originalData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView ageGroup;
        final TextView distance;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.itemName);
            ageGroup = (TextView)itemView.findViewById(R.id.itemAge);
            distance = (TextView)itemView.findViewById(R.id.itemDistance);
        }
    }
}
