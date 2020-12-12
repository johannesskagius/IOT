package com.example.node_red_01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.node_red_01.DSV.Building;
import com.example.node_red_01.DSV.Room;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;        //Skapar en fragment manager för att visa olika fragments
    private Building building = new Building();
    private int switchDeveloper = 2;
    private boolean isSet = false;
    private String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRooms();
        fragmentManager = getSupportFragmentManager();
        changeFragment(new HomeFragment(this));

    }

    private void loadRooms() {
        Room room = new Room(1, 1, 23, "Foobar");
        addRoom(room);
        Room room1 = new Room(2, 6, 23, "Seminarium 3");
        addRoom(room1);
        Room room2 = new Room(3, 1, 21, "Lunchrum");
        addRoom(room2);
        Room room3 = new Room(4, 1, 7, "L50");
        addRoom(room3);
        Room room4 = new Room(5, 6, 7, "MediaLabb");
        addRoom(room4);
        Room room5 = new Room(6, 4, 8, "Lilla hörsalen");
        addRoom(room5);
        Room room6 = new Room(7, 6, 16, "L70");
        addRoom(room6);
        Room room7 = new Room(8, 5, 23, "Entry1");
        addRoom(room7);
        Room room8 = new Room(9, 1, 23, "Trappa");
        addRoom(room8);
        Room room9 = new Room(10, 1, 23, "WC1");
        addRoom(room9);
        Room room10 = new Room(11, 1, 23, "WC2");
        addRoom(room10);
        Room room11 = new Room(12, 1, 23, "WC3");
        addRoom(room11);

    }

    public void changeFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, null).commit();
    }

    public String getPlace() {
        return place;
    }

    public void addRoom(Room r) {
        building.addPlace(r);
    }

    public Room getRoomByID(int id) {
        return building.getRoom(id);
    }

    public Object getRoomByName(String roomName) {
        return building.getRoom(roomName);
    }

    public ArrayList<String> getKeys() {
        return building.getKeys();
    }
}