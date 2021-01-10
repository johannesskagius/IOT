package com.example.node_red_01.Drone;

import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Drones {       //this is the drone class here we can store multiple drones and get the closest drone
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference();
    private final String DRONE_1_POSITION =("drone1");
    private final ArrayList<Drone> dronesList = new ArrayList<>();

    public void addDroneToList(Drone d){
        dronesList.add(d);
    }

    public Drone getDrone(int id){
        return dronesList.get(id);
    }

    public Drone getClosestDrone(Position position){    //this method collects the closest available drone
        int distanceAway= 150;
        int j = -1;
        Drone closestDrone = null;
        for(Drone d : dronesList){                      //Iterates through the arraylist of drone and returnin the closest drone to the position sent in.
            if(d.distanceAway(position) < distanceAway){
                distanceAway = d.distanceAway(position);
                closestDrone = d;
            }
            else closestDrone = dronesList.get(0);
            j++;
        }
        return closestDrone;
    }
}
