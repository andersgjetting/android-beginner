package dk.prosa.android.findplayground;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by andersgjetting on 17/02/2017.
 */

public class PlaygroundsAdapter extends RecyclerView.Adapter<PlaygroundsAdapter.ViewHolder>{

    final List<String> originalData;

    public PlaygroundsAdapter(List<String> originalData) {
        this.originalData = originalData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_playground, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        holder.name.setText(originalData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "item " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return originalData == null ? 0 : originalData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.itemName);
        }
    }
}
