package com.example.node_red_01.Drone;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.node_red_01.MainActivity;
import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Drone {
    private static final String POSITION_X = "position_X";
    private static final String POSITION_Y = "position_Y";
    private static final String NAME = "Name";
    private static final String TYPE = "Service";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference();
    private String []DONE_NAMES = {"drone1", "drone2"};
    private int ID;
    private String type;
    private int positionX;
    private int positionY;
    private Position dronePosition;
    private String refString;

    public Drone(int ID, int positionX, int positionY, String refString) {
        this.ID = ID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.refString = refString;
        dronePosition = new Position(positionX, positionY);
        uploadDroneToServer();
    }

    private void uploadDroneToServer() {
        DatabaseReference extended;
        if (ID==1){
        extended = myRef.child(DONE_NAMES[0]);
        }else{
            extended = myRef.child(DONE_NAMES[1]);
        }
        extended.child(NAME).setValue(ID);
        extended.child(TYPE).setValue(TYPE);
        extended.child(POSITION_X).setValue(positionX);
        extended.child(POSITION_Y).setValue(positionY);
    }

    public Drone(int ID, String type) {
        this.ID = ID;
        this.type = type;
    }

    public int distanceAway(Position startPosition) {
        return startPosition.getX() - dronePosition.getX() + startPosition.getY() + dronePosition.getY();
    }

    public void setDronePosition(Position dronePosition) {
        this.dronePosition = dronePosition;
        positionX = dronePosition.getX();
        positionY = dronePosition.getY();
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Position getDronePosition() {
        return dronePosition;
    }
}
