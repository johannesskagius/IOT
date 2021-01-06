package com.example.node_red_01.DSV;

import android.app.AppComponentFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Building extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("rooms");
    private final ArrayList<String> arrayList = new ArrayList<>();
    private final Map<Integer, Room> idPlace = new HashMap<>();
    private final Map<String, Room> nameToPlace = new HashMap<>();


    public void addPlace(Room p){
        idPlace.put(p.getID(), p);
        nameToPlace.put(p.getName(), p);
        arrayList.add(p.getName());
    }

    public Room getRoom(String name){
        return  nameToPlace.get(name);
    }
    public Room getRoom(int ID){
        return  idPlace.get(ID);
    }
    public ArrayList<String> getKeys(){
        return arrayList;
    }

}
