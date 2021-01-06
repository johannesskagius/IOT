package com.example.node_red_01;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.node_red_01.DSV.Waiting_time;
import com.example.node_red_01.Drone.Drone;
import com.example.node_red_01.RequestsAndPosition.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    private static final int SERVICE_PASSWORD = 12341234;
    private static final String ACTIVE_REQUEST = "active";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("request");
    private ArrayList<String> places;
    private AutoCompleteTextView salRequest_Auto;
    private ProgressBar progressBar;
    private final MainActivity main;
    private TextView ETA_textview;
    private final String place = "request";


    public HomeFragment(MainActivity main) {
        this.main = main;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, container, false);
        Button requestDroneButton = v.findViewById(R.id.button);
        requestDroneButton.setOnClickListener(view -> uploadRequest());
        progressBar = v.findViewById(R.id.progressBar);
        salRequest_Auto = v.findViewById(R.id.salNr);
        ETA_textview = v.findViewById(R.id.DroneETA);
        salRequest_Auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int number = Integer.parseInt(String.valueOf(charSequence));
                    if (number == SERVICE_PASSWORD) {
                        Toast.makeText(getContext(), "Show settings", Toast.LENGTH_SHORT).show();
                        main.changeFragment(new SettingsFragment(main));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        places = main.getKeys();
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, places);
        salRequest_Auto.setAdapter(adapter);
    }

    public void uploadRequest() {
        try {
            String salNr = salRequest_Auto.getText().toString();
            //GET START POS
            Position startPos = main.getPositionOfUnit();
            //GET room Pos
            Position finishPos = main.getRoomByName(salNr).getPosition();

            Map<String, String> users = new HashMap<>();
            users.put("startPos x", String.valueOf(startPos.getX()));
            users.put("startPos y", String.valueOf(startPos.getY()));
            users.put("Finish x", String.valueOf(finishPos.getX()));
            users.put("Finish y", String.valueOf(finishPos.getY()));
            users.put(ACTIVE_REQUEST, "active");

            myRef.setValue(users).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    getDroneStatus(finishPos);
                }
            }).addOnFailureListener(e -> Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDroneStatus(Position finishPos) {
        //Get closest drone
        Drone d = main.getClosestDrone(main.getPositionOfUnit());
        //Get drone ETA
        Waiting_time waiting_time = new Waiting_time(d.getDronePosition(), main.getPositionOfUnit(), finishPos);
        int waitingTime = waiting_time.getWaitingTime();

        //Update widget
        ETA_textview.setText("Drone will arrive in: " + waitingTime + "s");
        progressBar.setMax(waitingTime);
        progressBar.setProgress(waitingTime);
    }


}
