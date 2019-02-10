package nl.hva.msi.level2applicationgeoguessswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GeoObjectAdapter extends RecyclerView.Adapter<GeoObjectViewHolder> {

    private Context context;

    public List<GeoImage> geoImages;

    public GeoObjectAdapter(Context context, List<GeoImage> listGeoImages){
        context = context;
        geoImages = listGeoImages;
    }

    @NonNull
    @Override
    public GeoObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_cell, viewGroup, false);
        return new GeoObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoObjectViewHolder geoObjectViewHolder, int i) {
        final GeoImage geoImage = geoImages.get(i);
        geoObjectViewHolder.geoImage.setImageResource(geoImage.getGeoImageName());
    }

    @Override
    public int getItemCount() {
        return geoImages.size();
    }

    public void notifyIf
}
