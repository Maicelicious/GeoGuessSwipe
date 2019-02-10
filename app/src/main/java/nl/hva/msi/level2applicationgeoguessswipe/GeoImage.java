package nl.hva.msi.level2applicationgeoguessswipe;

public class GeoImage {
    private String geoName;
    private int geoImageName;

    public GeoImage(String geoName, int geoImageName) {
        this.geoName = geoName;
        this.geoImageName = geoImageName;
    }

    public String getGeoName() {
        return geoName;
    }

    public void setGeoName(String geoName) {
        this.geoName = geoName;
    }

    public int getGeoImageName() {
        return geoImageName;
    }

    public void setGeoImageName(int geoImageName) {
        this.geoImageName = geoImageName;
    }

    public static final String[] PRE_DEFINED_OBJECT_NAMES = {
            "YES_Denmark",
            "NO_Canada",
            "NO_Bangladesh",
            "YES_Kazachstan",
            "NO_Colombia",
            "YES_Poland",
            "YES_Malta",
            "NO_Thailand"
    };

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };
}
