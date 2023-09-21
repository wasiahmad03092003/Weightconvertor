package com.example.weightconvertor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText valueEditText;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueEditText = findViewById(R.id.valueEditText);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    private void convertWeight() {
        String valueStr = valueEditText.getText().toString();
        if (valueStr.isEmpty()) {
            resultTextView.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(valueStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double convertedValue = convert(value, fromUnit, toUnit);
        displayResult(convertedValue, toUnit);
    }

    private double convert(double value, String fromUnit, String toUnit) {
        // Define conversion factors for various weight units
        double kilogramToGram = 1000;
        double kilogramToPound = 2.20462;
        double gramToKilogram = 0.001;
        double gramToPound = 0.00220462;
        double poundToKilogram = 0.453592;
        double poundToGram = 453.592;

        if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) {
            return value * kilogramToGram;
        } else if (fromUnit.equals("Kilogram") && toUnit.equals("Pound")) {
            return value * kilogramToPound;
        } else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) {
            return value * gramToKilogram;
        } else if (fromUnit.equals("Gram") && toUnit.equals("Pound")) {
            return value * gramToPound;
        } else if (fromUnit.equals("Pound") && toUnit.equals("Kilogram")) {
            return value * poundToKilogram;
        } else if (fromUnit.equals("Pound") && toUnit.equals("Gram")) {
            return value * poundToGram;
        } else {
            return value; // Default to no conversion
        }
    }

    private void displayResult(double convertedValue, String toUnit) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = df.format(convertedValue) + " " + toUnit;
        resultTextView.setText(result);
    }
}
