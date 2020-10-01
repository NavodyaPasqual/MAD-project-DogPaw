package com.example.dogpaw;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomersMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient googleApiClient;
    Location lastLocation;
    LocationRequest locationRequest;

    private Button customerLogOutButton;
    private Button calltaxibutton;
    private Button settingbutton;

    private String customerID;
    private LatLng customerPickuplocation;
    private int radius = 1;
    private Boolean driverFound = false, requestType = false;
    //private String driverFoundID;
    private String driverID = "";


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    Marker driverMarker, PickUpMarker;
    ;
    private DatabaseReference customerDatabaseref;
    private DatabaseReference driverAvailableRef;
    private DatabaseReference driverRef;
    private DatabaseReference driverLocationRef;
    private ValueEventListener driverLocationRefListner;
    GeoQuery geoQuery;

    private TextView txtname, txtvehicleinfo, txtphone;
    private CircleImageView propic;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_map);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        customerDatabaseref = FirebaseDatabase.getInstance().getReference().child("Customers Req");
        driverAvailableRef = FirebaseDatabase.getInstance().getReference().child("Drivers Available");
        driverLocationRef = FirebaseDatabase.getInstance().getReference().child("Drivers Working");


        customerLogOutButton = (Button) findViewById(R.id.logout_customer_btn);
        settingbutton = (Button) findViewById(R.id.settings_customer_btn);
        calltaxibutton = (Button) findViewById(R.id.customer_hail_taxi_btn);

        txtname = findViewById(R.id.name_driver);
        txtphone = findViewById(R.id.phone_driver);
        txtvehicleinfo = findViewById(R.id.car_name_driver);
        propic = findViewById(R.id.profile_image_driver);
        relativeLayout = findViewById(R.id.displayinfo1);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomersMapActivity.this, SettingsActivity.class);
                intent.putExtra("type", "Customers");
                startActivity(intent);
            }
        });

        customerLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Logoutcustomer();
            }
        });

        calltaxibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestType) {
                    requestType = false;
                    geoQuery.removeAllListeners();
                    driverLocationRef.removeEventListener(driverLocationRefListner);

                    if (driverFound != null) {
                        driverRef = FirebaseDatabase.getInstance().getReference().child("Clients").child("Drivers").child(driverID).child("CustomerRiderID");
                        //
                        driverRef.setValue(true);
                        //

                        driverRef.removeValue();

                        driverID = null;

                    }
                    driverFound = false;
                    radius = 1;
                    //
                    String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //


                    GeoFire geoFire = new GeoFire(customerDatabaseref);
                    geoFire.removeLocation(customerId);

                    if (PickUpMarker != null) {
                        PickUpMarker.remove();
                    }
                    if (driverMarker != null) {
                        driverMarker.remove();
                    }
                    calltaxibutton.setText("Call a CAB");
                    relativeLayout.setVisibility(View.GONE);


                } else {
                    requestType = true;

                    //
                    String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //

                    GeoFire geoFire = new GeoFire(customerDatabaseref);
                    geoFire.setLocation(customerId, new GeoLocation(lastLocation.getLatitude(), lastLocation.getLongitude()));

                    customerPickuplocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    PickUpMarker = mMap.addMarker(new MarkerOptions().position(customerPickuplocation).title("PICK ME UP HERE").icon(BitmapDescriptorFactory.fromResource(R.drawable.user)));
                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.car))

                    calltaxibutton.setText("Getting your Driver");
                    GetclosestDriver();

                }

            }
        });


    }

    private void GetclosestDriver() {
        GeoFire geoFire = new GeoFire(driverAvailableRef);
        //GeoQuery
        geoQuery = geoFire.queryAtLocation(new GeoLocation(customerPickuplocation.latitude, customerPickuplocation.longitude), radius);
        geoQuery.removeAllListeners();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                //everytime the driver is called this method will be called
                //key=driverID and the location
                //if(!driverFound && requestType)
                if (!driverFound && requestType) {
                    driverFound = true;
                    driverID = key;
                    //this tell driver which customer he is going to have
                    driverRef = FirebaseDatabase.getInstance().getReference().child("Clients").child("Drivers").child(driverID);
                    HashMap driverMap = new HashMap();
                    driverMap.put("CustomerRiderID", customerID);
                    driverRef.updateChildren(driverMap);

                    //Show driver location on customerMapActivity
                    GettingDriverLocation();
                    calltaxibutton.setText("Searching For Driver");
                    //
                    //calltaxibutton.setText("Driver Arrived");


                }

            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                if (!driverFound) {
                    radius = radius + 1;
                    GetclosestDriver();
                }

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

    //and then we get to the driver location - to tell customer where is the driver
    private void GettingDriverLocation() {

        driverLocationRefListner = driverLocationRef.child(driverID).child("l").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && requestType) {
                    List<Object> driverLocationMap = (List<Object>) dataSnapshot.getValue();
                    double locationLat = 0;
                    double locationLng = 0;
                    calltaxibutton.setText("Driver on the way");
                    relativeLayout.setVisibility(View.VISIBLE);
                    getassigndriverinfo();

                    if (driverLocationMap.get(0) != null) {
                        locationLat = Double.parseDouble(driverLocationMap.get(0).toString());
                    }
                    if (driverLocationMap.get(1) != null) {
                        locationLng = Double.parseDouble(driverLocationMap.get(1).toString());
                    }
                    //adding marker - to pointing where driver is - using this lat lng
                    LatLng driverLatLng = new LatLng(locationLat, locationLng);
                    //

                    if (driverMarker != null) {
                        driverMarker.remove();
                    }

                    Location location1 = new Location("");
                    location1.setLatitude(customerPickuplocation.latitude);
                    location1.setLongitude(customerPickuplocation.longitude);

                    Location location2 = new Location("");
                    location2.setLatitude(driverLatLng.latitude);
                    location2.setLongitude(driverLatLng.longitude);

                    float Distance = location1.distanceTo(location2); //calculate distance

                    if (Distance < 90) {
                        calltaxibutton.setText("Driver Arrived");
                    } else {
                        calltaxibutton.setText("Driver Found:" + String.valueOf(Distance));
                    }

                    driverMarker = mMap.addMarker(new MarkerOptions().position(driverLatLng).title("Driver Arrived").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        buildGoogleApiClient();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    @Override
    public void onLocationChanged(Location location)
    {
        //getting the updated location
        lastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

    }
    //method for using apis
    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        googleApiClient.connect();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }


    private void getassigndriverinfo()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Clients").child("Drivers").child(driverID);
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()  && dataSnapshot.getChildrenCount() > 0)
                {

                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    String vehicle = dataSnapshot.child("Vehicle").getValue().toString();
                    txtname.setText(name);
                    txtphone.setText(phone);
                    txtvehicleinfo.setText(vehicle);

                    if (dataSnapshot.hasChild("image"))
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(propic);
                    }
                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError)
            {

            }
        });
    }
    private void Logoutcustomer()
    {

        Intent startPageIntent = new Intent(CustomersMapActivity.this, WelcomeActivity.class);
        startPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startPageIntent);
        finish();

    }
}
