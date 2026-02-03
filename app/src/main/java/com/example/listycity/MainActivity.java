package com.example.listycity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Collections;
public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Montréal"};
        dataList = new ArrayList<>();
        Collections.addAll(dataList, cities);
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Toast.makeText(this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });
        Button addButton = findViewById(R.id.add_city_button);
        addButton.setOnClickListener(v -> showAddCityDialog());
        Button deleteButton = findViewById(R.id.delete_city_button);
        deleteButton.setOnClickListener(v -> deleteSelectedCity());
    }
    private void showAddCityDialog() {
        final EditText input = new EditText(this);
        input.setHint("Enter city name");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add City")
                .setView(input)
                .setPositiveButton("CONFIRM", (dialog, which) -> {
                    String cityName = input.getText().toString().trim();
                    if (!cityName.isEmpty()) {
                        dataList.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                        Toast.makeText(this, cityName + " added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "City name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel())
                .show();
    }
    private void deleteSelectedCity() {
        if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
            String removedCity = dataList.remove(selectedPosition);
            cityAdapter.notifyDataSetChanged();
            Toast.makeText(this, removedCity + " deleted", Toast.LENGTH_SHORT).show();
            selectedPosition = -1; // Reset selection
        } else {
            Toast.makeText(this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
        }
    }
}