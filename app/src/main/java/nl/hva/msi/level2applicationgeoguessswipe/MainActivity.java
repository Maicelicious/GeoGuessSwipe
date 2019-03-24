package nl.hva.msi.level2applicationgeoguessswipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<GeoImage> geoImages;

    Context context;

    public MainActivity() {
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geoImages = new ArrayList<>();

        for (int i = 0; i < GeoImage.PRE_DEFINED_IMAGE_NAMES.length; i++) {
            geoImages.add(new GeoImage(GeoImage.PRE_DEFINED_IMAGE_NAMES[i], GeoImage.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

        final RecyclerView.LayoutManager recycleViewLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(recycleViewLayoutManager);
        recyclerView.setHasFixedSize(true);
        final GeoObjectAdapter geoObjectAdapter = new GeoObjectAdapter(this, geoImages);
        recyclerView.setAdapter(geoObjectAdapter);

        /*
            Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
            An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
            and uses callbacks to signal when a user is performing these actions.
        */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        ((StaggeredGridLayoutManager) recycleViewLayoutManager).scrollToPositionWithOffset(2,20);
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        GeoImage geoImage = geoImages.get(position);
                        if (geoImage == null) {
                            reset();
                            return;
                        }

                        boolean isInEurope = checkIfGeoImageIsInEurope(geoImage.getGeoName());
                        if (swipeDir == ItemTouchHelper.LEFT && isInEurope || swipeDir == ItemTouchHelper.RIGHT && !isInEurope) {
                            Toast.makeText(context, "You are right next one! Country: " + geoImage.getGeoName(), Toast.LENGTH_SHORT).show();
                            geoImages.remove(geoImage);
                            geoObjectAdapter.geoImages.remove(geoImage);
                        } else{
                            Toast.makeText(context, "You are wrong try again!", Toast.LENGTH_SHORT).show();
                        }
                        geoObjectAdapter.notifyDataSetChanged();
                        if (geoObjectAdapter.getItemCount() == 0){
                            reset();
                            return;
                        }
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void reset() {
        for (int i = 0; i < GeoImage.PRE_DEFINED_IMAGE_NAMES.length; i++) {
            geoImages.add(new GeoImage(GeoImage.PRE_DEFINED_IMAGE_NAMES[i], GeoImage.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }
        Toast.makeText(this, "New Round!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkIfGeoImageIsInEurope(String geoName) {
        if (geoName.substring(0, 1).equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
}
