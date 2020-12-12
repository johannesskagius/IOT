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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private static final int SERVICE_PASSWORD = 12341234;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("request");
    private ArrayList<String> places;
    private AutoCompleteTextView salRequest_editText;
    private MainActivity main;
    private String place;


    public HomeFragment(MainActivity main) {
        this.main = main;
        this.place = main.getPlace();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, container, false);
        Button requestDroneButton = v.findViewById(R.id.button);
        requestDroneButton.setOnClickListener(view -> connectToRealtime());
        salRequest_editText = v.findViewById(R.id.salNr);
        salRequest_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int number = Integer.parseInt(String.valueOf(charSequence));
                    if(number == SERVICE_PASSWORD){
                        Toast.makeText(getContext(), "Show settings", Toast.LENGTH_SHORT).show();
                        main.changeFragment(new SettingsFragment(main));
                    }
                }catch (Exception e){
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
    }

    public void connectToRealtime() {
        try {
            String salNr = salRequest_editText.getText().toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(place, salNr);

            myRef.child(place).setValue(salNr);

            myRef.setValue(jsonObject).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    getDroneStatus();
                }
            }).addOnFailureListener(e -> Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDroneStatus() {
        //TODO check which drone is closest
        //TODO get drone ETA
        //TODO Update widget
    }
}
