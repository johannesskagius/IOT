package com.example.node_red_01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.node_red_01.DSV.Building;
import com.example.node_red_01.DSV.Room;
import com.example.node_red_01.RequestsAndPosition.Position;
import com.example.node_red_01.drone.Drone;
import com.example.node_red_01.drone.Drones;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;        //Skapar en fragment manager för att visa olika fragments
    private Building building = new Building();
    private Drones drones = new Drones();
    private int switchDeveloper = 2;
    private boolean isSet = false;
    private Position positionOfUnit = new Position(1,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRooms();
        loadDrones();
        fragmentManager = getSupportFragmentManager();
        changeFragment(new HomeFragment(this));

    }

    private void loadDrones() {
        Drone drone1 = new Drone(1, 1,1);
        Drone drone2 = new Drone(2, 5,6);
        drones.addDroneToList(drone1);
        drones.addDroneToList(drone2);
    }

    private void loadRooms() {
        Room room = new Room(1, new Position(1,23), "Foobar");
        addRoom(room);
        Room room1 = new Room(2, new Position(6,23), "Seminarium 3");
        addRoom(room1);
        Room room2 = new Room(3, new Position(1,21), "Lunchrum");
        addRoom(room2);
        Room room3 = new Room(4, new Position(1,7), "L50");
        addRoom(room3);
        Room room4 = new Room(5, new Position(6,7), "MediaLabb");
        addRoom(room4);
        Room room5 = new Room(6, new Position(4,8), "Lilla hörsalen");
        addRoom(room5);
        Room room6 = new Room(7, new Position(6,16), "L70");
        addRoom(room6);
        Room room7 = new Room(8, new Position(5,23), "Entry1");
        addRoom(room7);
        Room room8 = new Room(9, new Position(4,3), "Trappa");
        addRoom(room8);
        Room room9 = new Room(10, new Position(3,23), "WC1");
        addRoom(room9);
        Room room10 = new Room(11, new Position(6,3), "WC2");
        addRoom(room10);
        Room room11 = new Room(12, new Position(5,13), "WC3");
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

    public Drone getClosestDrone(Position p){
        return drones.getClosestDrone(p);
    }
}