package com.example.node_red_01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.node_red_01.DSV.Room;
import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment {//This is the administrator fragment. When a user types 12341234 this fragment shows. The user can set the unitID and the position of the unit.
    private EditText ID_editText;
    private EditText position_X;
    private EditText position_Y;
    private EditText room_name;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final MainActivity main;

    public SettingsFragment(MainActivity main) {
        this.main = main;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout, container, false);
        ID_editText = v.findViewById(R.id.autoComplete_ID);
        position_X = v.findViewById(R.id.position_X_in);
        position_Y = v.findViewById(R.id.position_Y_in);
        room_name = v.findViewById(R.id.place_Name_In);
        Button add = v.findViewById(R.id.AddPosition);
        add.setOnClickListener(view -> addPosition());
        Button finishes = v.findViewById(R.id.Finished);
        finishes.setOnClickListener(view -> goBack());
        return v;
    }



    private void goBack() {
        main.changeFragment(new HomeFragment(main));
    }

    private void addPosition() {
        try {
            int unitID = Integer.parseInt(ID_editText.getText().toString());
            int x  = Integer.parseInt(position_X.getText().toString());
            int y = Integer.parseInt(position_Y.getText().toString());
            Position newPosition = new Position(x,y);
            main.setPositionOfUnit(newPosition);

            Room newRoom = new Room(unitID,newPosition, room_name.getText().toString());
            main.addRoom(newRoom);

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLocation();
    }

    private void setLocation(){
        String x = String.valueOf(main.getPositionOfUnit().getX());
        String y = String.valueOf(main.getPositionOfUnit().getY());
        position_X.setText(x);
        position_Y.setText(y);
    }
}
