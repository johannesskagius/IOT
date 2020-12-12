package com.example.node_red_01.drone;

import com.example.node_red_01.RequestsAndPosition.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drones {
    private ArrayList<Drone> dronesList = new ArrayList<>();

    public void addDroneToList(Drone d){
        dronesList.add(d);
    }

    public Drone getDrone(int id){
        return dronesList.get(id);
    }

    public Drone getClosestDrone(Position position){
        int distanceAway= 15;
        int j = 0;
        for(Drone d : dronesList){
            if(d.distanceAway(position) > distanceAway)
                distanceAway = d.distanceAway(position);
            j++;
        }
        return dronesList.get(j);
    }
}
