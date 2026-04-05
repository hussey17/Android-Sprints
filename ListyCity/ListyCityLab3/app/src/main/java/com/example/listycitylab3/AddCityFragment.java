package com.example.listycitylab3;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City city);
        void editCity(City city, String newName, String newProvince);
    }
    private AddCityDialogListener listener;
    // Factory method to create a fragment for editing an existing city
    static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        // Check if we are editing an existing city
        City existingCity = null;
        if (getArguments() != null) {
            existingCity = (City) getArguments().getSerializable("city");
        }
        // If editing, pre-fill the fields with the existing city data
        boolean isEditing = (existingCity != null);
        if (isEditing) {
            editCityName.setText(existingCity.getName());
            editProvinceName.setText(existingCity.getProvince());
        }
        String title = isEditing ? "Edit City" : "Add a city";
        String positiveButton = isEditing ? "Save" : "Add";
        final City cityToEdit = existingCity;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(positiveButton, (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    if (isEditing) {
                        listener.editCity(cityToEdit, cityName, provinceName);
                    } else {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}