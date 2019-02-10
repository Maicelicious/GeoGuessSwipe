package nl.hva.msi.level2applicationgeoguessswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<GeoImage> geoImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geoImages = new ArrayList<>();

        for (int i = 0; i < GeoImage.PRE_DEFINED_OBJECT_NAMES.length; i++) {
            geoImages.add(new GeoImage(GeoImage.PRE_DEFINED_OBJECT_NAMES[i], GeoImage.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        final RecyclerView recyclerView = findViewById(R.id.recycleView);

        RecyclerView.LayoutManager recycleViewLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(recycleViewLayoutManager);
        recyclerView.setHasFixedSize(true);
        final GeoObjectAdapter geoObjectAdapter = new GeoObjectAdapter(this, geoImages);
        recyclerView.setAdapter(geoObjectAdapter);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override

                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                        return false;

                    }

                    //Called when a user swipes left or right on a ViewHolder

                    @Override

                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());

                    }

                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
}
