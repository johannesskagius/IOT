package com.example.node_red_01.drone;

import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Drone {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("request");
    private int ID;
    private String type;
    private int positionX;
    private int positionY;
    private Position dronePosition;

    public Drone(int ID, int positionX, int positionY) {
        this.ID = ID;
        this.positionX = positionX;
        this.positionY = positionY;
        dronePosition = new Position(positionX, positionY);
    }

    public int distanceAway(Position startPosition){
        return startPosition.getX()-dronePosition.getX()+startPosition.getY()+dronePosition.getY();
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Position getDronePosition() {
        return dronePosition;
    }
}
