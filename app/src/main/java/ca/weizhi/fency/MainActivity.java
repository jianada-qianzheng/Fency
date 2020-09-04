package ca.weizhi.fency;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.navnas.barcodereader.R;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "MyLocationService";
//    private LocationManager mLocationManager = null;
//    private static final int LOCATION_INTERVAL = 1000;
//    private static final float LOCATION_DISTANCE = 10f;
//
//    Location mLastLocation;
//
//    private class LocationListener implements android.location.LocationListener {
//        //Location mLastLocation;
//
//        public LocationListener(String provider) {
//            Log.e(TAG, "LocationListener " + provider);
//            mLastLocation = new Location(provider);
//        }
//
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.e(TAG, "onLocationChanged: " + location);
//            mLastLocation.set(location);
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            Log.e(TAG, "onProviderDisabled: " + provider);
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            Log.e(TAG, "onProviderEnabled: " + provider);
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Log.e(TAG, "onStatusChanged: " + provider);
//        }
//    }
//
//    LocationListener[] mLocationListeners = new LocationListener[]{
//            new LocationListener(LocationManager.PASSIVE_PROVIDER)
//    };
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());


        SQLiteDatabase db = dbhelper.getWritableDatabase();





        //initializeLocationManager();


//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
//                PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
//                        PackageManager.PERMISSION_GRANTED) {
//
//            mLocationManager.requestLocationUpdates(
//                    LocationManager.PASSIVE_PROVIDER,
//                    LOCATION_INTERVAL,
//                    LOCATION_DISTANCE,
//                    mLocationListeners[0]
//            );
//        } else {
//            ActivityCompat.requestPermissions(this, new String[] {
//                            Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION },
//                   123); //TAG_CODE_PERMISSION_LOCATION);//todo
//        }



        Intent intent = new Intent(this, KeyCodeUnlock.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);

        } else {
            startService(intent);

        }




    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        Intent intent = new Intent(MainActivity.this, KeyCodeUnlock.class);
//        stopService(intent);
    }

//    private void initializeLocationManager() {
//        Log.e(TAG, "initializeLocationManager - LOCATION_INTERVAL: "+ LOCATION_INTERVAL + " LOCATION_DISTANCE: " + LOCATION_DISTANCE);
//        if (mLocationManager == null) {
//            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        }
//    }

}
