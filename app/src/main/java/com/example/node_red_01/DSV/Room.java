package com.example.node_red_01.DSV;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Room extends Building {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("rooms");
    private final int width = 2;
    private final int height = 2;
    private int ID;
    private int position_X;
    private int position_Y;
    private String name;

    public Room(int ID, int position_X, int position_Y, String name) {
        this.ID = ID;
        this.position_X = position_X;
        this.position_Y = position_Y;
        this.name = name;
        addRoomToDatabase();
    }

    private void addRoomToDatabase() {
        myRef.child(name).child("ID").setValue(ID);
        myRef.child(name).child("position_X").setValue(position_X);
        myRef.child(name).child("position_Y").setValue(position_Y);
        myRef.child(name).child("name").setValue(name);
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

    public int getPosition_X() {
        return position_X;
    }

    public int getPosition_Y() {
        return position_Y;
    }

    public String getName() {
        return name;
    }

    public JSONObject getJsonObject(){
            JSONObject newObj = new JSONObject();
        try {
            newObj.put("ID", String.valueOf(ID));
            newObj.put("Name", name);
            newObj.put("PositionX", String.valueOf(position_X));
            newObj.put("PositionY", String.valueOf(position_Y));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newObj;
    }
}
