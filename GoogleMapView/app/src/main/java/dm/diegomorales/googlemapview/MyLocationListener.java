package dm.diegomorales.googlemapview;

import android.location.Location;
import android.location.LocationListener;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MyLocationListener implements LocationListener {
    private TextView textView;
    public MyLocationListener(TextView textView) {
        this.textView=textView;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        textView.setText(location.toString());

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
