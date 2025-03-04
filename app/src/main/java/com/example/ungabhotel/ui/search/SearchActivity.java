package com.example.ungabhotel.ui.search;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.example.ungabhotel.R;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {
    // room type, start and end week
    private Button dateBtn;
    private Button searchBtn;
    private EditText startDateField, endDateField;
    private boolean isSelectingStart = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitializeComponents();
    }

    private void InitializeComponents() {
        searchBtn = findViewById(R.id.searchBtn);
        dateBtn = findViewById(R.id.dateBtn);
        startDateField = findViewById(R.id.startDateText);
        endDateField = findViewById(R.id.endDateField);

        dateBtn.setOnClickListener(view -> {
            isSelectingStart = true;
            showDatePicker();
        });
    }

    private void showDatePicker()
    {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void startDateSelection(int year, int month, int day)
    {
        String selectedDate = year + "/" + (month + 1) + "/" + day;

        if(isSelectingStart) {
            startDateField.setText(selectedDate);
            isSelectingStart = false;
            showDatePicker();
        } else {
            endDateField.setText(selectedDate);
        }
    }

    private void DisplaySearchResult()
    {
    }
}