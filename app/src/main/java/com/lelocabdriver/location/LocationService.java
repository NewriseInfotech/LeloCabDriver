package com.lelocabdriver.location;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.lelocabdriver.utils.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 23-09-2016.
 */

public class LocationService extends Service
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private long mLastLocationTimeStamp;
    private long startTime;
    private long lastActiveTime;
    private int speed = 0;

    private boolean isAboveThreshold = false;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        appendLog("Location service start");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        googleApiClient.connect();
        return START_STICKY;
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000 * 10);
        mLocationRequest.setFastestInterval(1000 * 5);
//        mLocationRequest.setSmallestDisplacement((float) 0.25);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);

        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            onLocationChanged(lastLocation);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("GoogleAPICLIENT", "Connection failed: " + connectionResult.toString());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        googleApiClient.disconnect();
    }

    float distanceInKms;
    long durationInHrs;

    @Override
    public void onLocationChanged(final Location location) {
        if (location == null) return;

        if (mLastLocation != null) {
            distanceInKms = mLastLocation.distanceTo(location) / 1000;
            durationInHrs = (System.currentTimeMillis() - mLastLocationTimeStamp);
            speed = (int) (distanceInKms * (1000 * 60 * 60) / durationInHrs);
            appendLog("Location is " + location.getLatitude() + " " + location.getLongitude());
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(getApplicationContext(), (distanceInKms * 1000) + "\n" + speed, Toast.LENGTH_SHORT).show();
                }
            });
            sendUpdatedLocation(getApplicationContext(), location, speed, distanceInKms);
        }
        mLastLocationTimeStamp = System.currentTimeMillis();
        mLastLocation = location;
    }


    public static void appendLog(String text) {
        File logFile = new File(Environment.getExternalStorageDirectory() + "/log_v3.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendUpdatedLocation(Context context, Location location, int speed, float distanceInKms) {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> services = activityManager
                .getRunningTasks(Integer.MAX_VALUE);
        boolean isActivityFound = false;

        if (services.get(0).topActivity.getPackageName().toString()
                .equalsIgnoreCase(getApplicationContext().getPackageName().toString())) {
            isActivityFound = true;
        }


        Intent intent = new Intent(Constants.UPDATE_LOCATION);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

}

