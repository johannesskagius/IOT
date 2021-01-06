package com.example.node_red_01.DSV;

import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Room extends Building {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("rooms");
    private final int width = 2;
    private final int height = 2;
    private final int ID;
    private final Position position;
    private final String name;

    public Room(int ID, Position position, String name) {
        this.ID = ID;
        this.position = position;
        this.name = name;
        addRoomToDatabase();
    }

    private void addRoomToDatabase() {
        myRef.child(name).child("ID").setValue(ID);
        myRef.child(name).child("position_X").setValue(position.getX());
        myRef.child(name).child("position_Y").setValue(position.getY());
        myRef.child(name).child("name").setValue(name);
    }

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

}
