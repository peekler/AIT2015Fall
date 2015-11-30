package hu.bme.aut.android.locationdemo;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LocationListener {

    private LocationManager locationManager;
    private TextView tvDistance;
    private TextView tvLocData;
    private Button btnStart;
    private Button btnStop;
    private Button btnGeoCode;

    private Location prevLocation = null;
    private float distance = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(
                LOCATION_SERVICE);

        tvDistance = (TextView) findViewById(R.id.tvDistance);
        tvLocData = (TextView) findViewById(R.id.tvLocData);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationMonitoring();
            }
        });
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationMonitoring();
            }
        });

        btnGeoCode = (Button) findViewById(R.id.btnGeoCode);
        btnGeoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prevLocation != null) {
                    new Thread() {
                        public void run() {
                            final String address = getAddress(
                                    prevLocation.getLatitude(),
                                    prevLocation.getLongitude());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,
                                            address,
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }.start();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationMonitoring();
    }

    private void startLocationMonitoring() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0, this);
        }catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void stopLocationMonitoring() {
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        String lat = String.valueOf(location.getLatitude());
        String lng = String.valueOf(location.getLongitude());
        String speed = String.valueOf(location.getSpeed());
        String accuracy = String.valueOf(location.getAccuracy());
        String altitude = String.valueOf(location.getAltitude());
        String provider = location.getProvider();

        tvLocData.setText(
                "Provider: " + provider + "\n" +
                        "Accuracy: " + accuracy + "\n" +
                        "Lat: " + lat + "\n" +
                        "Long: " + lng + "\n" +
                        "Speed: " + speed + "\n" +
                        "Altitude: " + altitude
        );

        if (prevLocation != null) {
            distance += location.distanceTo(prevLocation);
            tvDistance.setText("Distance taken (m): " + distance);
        }

        prevLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(MainActivity.this, provider+", status: "+status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(MainActivity.this, provider + " enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, provider + " disabled", Toast.LENGTH_SHORT).show();
    }

    private String getAddress(double lat, double lng) {
        String result = "empty";
        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> addressList =
                    geocoder.getFromLocation(lat, lng, 1);
            result = addressList.get(0).getAddressLine(0)+"\n"+
                    addressList.get(0).getAddressLine(1)+"\n"+
                    addressList.get(0).getAddressLine(2);
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }
}
