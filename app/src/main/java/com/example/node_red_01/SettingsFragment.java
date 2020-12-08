package com.example.node_red_01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment {
    private AutoCompleteTextView ID_AutoComplete;
    private AutoCompleteTextView position_AutoComplete;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private MainActivity main;

    public SettingsFragment(MainActivity main) {
        this.main = main;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout, container, false);
        ID_AutoComplete = v.findViewById(R.id.autoComplete_ID);
        position_AutoComplete = v.findViewById(R.id.autoComplete_Position);
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
        String unitID = ID_AutoComplete.getText().toString();
        String place = position_AutoComplete.getText().toString();

        DatabaseReference myRef = database.getReference(place);
        main.setPlace(place);
        myRef.setValue(place).addOnCompleteListener(task -> {
            if(task.isSuccessful())
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e-> Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
