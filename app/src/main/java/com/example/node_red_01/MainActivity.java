package com.example.node_red_01;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.node_red_01.DSV.Building;
import com.example.node_red_01.DSV.Room;
import com.example.node_red_01.Drone.Drone;
import com.example.node_red_01.Drone.Drones;
import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String POSITION_X = "position_X";
    private static final String POSITION_Y = "position_Y";
    private static final String TAG = "SERVER_EVENT";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference drone2Listener = database.getReference("drone2");
    private DatabaseReference drone1Listener = database.getReference("drone1");
    private Drone drone1;
    private Drone drone2;

    private FragmentManager fragmentManager;        //Skapar en fragment manager för att visa olika fragments
    private Building building = new Building();
    private Drones drones = new Drones();
    private int switchDeveloper = 2;
    private boolean isSet = false;
    private Position positionOfUnit = new Position(5, 23);
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRooms();
        loadDrones();
        fragmentManager = getSupportFragmentManager();
        changeFragment(new HomeFragment(this));
        setDroneListener();
    }

    private void setDroneListener() {
        try {
            drone2Listener.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String value = snapshot.child(POSITION_X).getValue().toString();
                    Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    String value2 = snapshot.child(POSITION_Y).getValue().toString();
                    Toast.makeText(MainActivity.this, value2, Toast.LENGTH_SHORT).show();
                    updateDroneLocation(value, value2, drone2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            drone1Listener.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String value = snapshot.child(POSITION_X).getValue().toString();
                    Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    String value2 = snapshot.child(POSITION_Y).getValue().toString();
                    Toast.makeText(MainActivity.this, value2, Toast.LENGTH_SHORT).show();
                    updateDroneLocation(value, value2, drone1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateDroneLocation(String x, String y, Drone d){
        try {
                 d.setDronePosition(new Position(Integer.parseInt(x),Integer.parseInt(y)));
        }   catch (NumberFormatException e){
               e.printStackTrace();
        }
    }

    private void loadDrones() {
        drone1 = new Drone(1, 5, 17, "drone1");
        drone2 = new Drone(2, 16, 3, "drone2");
        drones.addDroneToList(drone1);
        drones.addDroneToList(drone2);
    }

    private void loadRooms() {
        Room room = new Room(1, new Position(1, 23), "Foobar");
        addRoom(room);
        Room room1 = new Room(2, new Position(6, 23), "Seminarium 3");
        addRoom(room1);
        Room room2 = new Room(3, new Position(1, 21), "Lunchrum");
        addRoom(room2);
        Room room3 = new Room(4, new Position(1, 7), "L50");
        addRoom(room3);
        Room room4 = new Room(5, new Position(6, 7), "MediaLabb");
        addRoom(room4);
        Room room5 = new Room(6, new Position(4, 8), "Lilla hörsalen");
        addRoom(room5);
        Room room6 = new Room(7, new Position(6, 16), "L70");
        addRoom(room6);
        Room room7 = new Room(8, new Position(5, 23), "Entry1");
        addRoom(room7);
        Room room8 = new Room(9, new Position(4, 3), "Trappa");
        addRoom(room8);
        Room room9 = new Room(10, new Position(3, 23), "WC1");
        addRoom(room9);
        Room room10 = new Room(11, new Position(6, 3), "WC2");
        addRoom(room10);
        Room room11 = new Room(12, new Position(5, 13), "WC3");
        addRoom(room11);
    }

    public void changeFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, null).commit();
    }

    public void addRoom(Room r) {
        building.addPlace(r);
    }

    public Room getRoomByID(int id) {
        return building.getRoom(id);
    }

    public Room getRoomByName(String roomName) {
        return building.getRoom(roomName);
    }

    public ArrayList<String> getKeys() {
        return building.getKeys();
    }

    public Position getPositionOfUnit() {
        return positionOfUnit;
    }

    public void setPositionOfUnit(Position positionOfUnit) {
        this.positionOfUnit = positionOfUnit;
    }

    public Drone getClosestDrone(Position p) {
        return drones.getClosestDrone(p);
    }

}