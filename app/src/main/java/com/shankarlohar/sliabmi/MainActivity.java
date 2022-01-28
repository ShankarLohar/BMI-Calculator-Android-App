package com.shankarlohar.sliabmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private TextView resultText;
    private RadioButton maleButton, femaleButton;
    private EditText ageText, feetText, inchText, weightText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupOnClickListener();
    }

    private void setupOnClickListener() {
        calculateButton.setOnClickListener(view -> {
            double calculatedBmi =  calculateBmi();

            String getAgeText = ageText.getText().toString();
            int age = Integer.parseInt(getAgeText);

            if(age>=18) displayResult(calculatedBmi);
            else displayGuidance(calculatedBmi);

        });
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageText = findViewById(R.id.edit_text_age);
        feetText = findViewById(R.id.edit_text_feet);
        inchText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private double calculateBmi() {
        String getFeetText = feetText.getText().toString();
        String getInchesText = inchText.getText().toString();
        String getWeightText = weightText.getText().toString();

        int feet = Integer.parseInt(getFeetText);
        int inches = Integer.parseInt(getInchesText);
        int weight = Integer.parseInt(getWeightText);

        double heightInMeters = ((feet * 12) + inches) * 0.0254;
        return weight / (heightInMeters * heightInMeters);

    }
    private void displayResult(double calculatedBmi){
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiToText = decimalFormatter.format(calculatedBmi);

        String resultString;

        if(calculatedBmi < 18.5) resultString = bmiToText + " - You are underweight!";
        else if(calculatedBmi > 25) resultString = bmiToText + " - You are overweight!";
        else resultString = bmiToText + " - You are perfect weight!";

        resultText.setText(resultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiToText = decimalFormatter.format(bmi);
        String resultString;
        if (maleButton.isChecked()) resultString = bmiToText + " - As you are under 18, please consult a doctor for the healthy range for boys";
        else if (femaleButton.isChecked()) resultString = bmiToText + " - As you are under 18, please consult a doctor for the healthy range for girls";
        else resultString = bmiToText + " - As you are under 18, please consult a doctor for the healthy range";

        resultText.setText(resultString);
    }
}
