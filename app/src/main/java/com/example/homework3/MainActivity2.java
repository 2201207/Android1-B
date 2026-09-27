package com.example.homework3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        View root = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            | WindowInsetsCompat.Type.ime());

            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        EditText editBirthYear = findViewById(R.id.editBirthYear);
        EditText editAge = findViewById(R.id.editAge);
        Button btnCalculateAge = findViewById(R.id.btnCalculateAge);
        Button btnCalculateYear = findViewById(R.id.btnCalculateYear);

        btnCalculateAge.setOnClickListener(v -> {
            String input = editBirthYear.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(
                        this,
                        "출생 연도를 입력해주세요.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int birthYear = Integer.parseInt(input);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            if (birthYear < 0 || birthYear > currentYear) {
                Toast.makeText(
                        this,
                        "출생 연도는 0부터 " + currentYear + "년까지 입력해주세요.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            int age = currentYear - birthYear;

            Toast.makeText(
                    this,
                    "당신의 나이는 " + age + "세입니다.",
                    Toast.LENGTH_LONG
            ).show();
        });

        btnCalculateYear.setOnClickListener(v -> {
            String input = editAge.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(
                        this,
                        "나이를 입력해주세요.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int age = Integer.parseInt(input);

            if (age < 1 || age > 130) {
                Toast.makeText(
                        this,
                        "나이는 1부터 130까지 입력해주세요.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int birthYear = currentYear - age;

            Toast.makeText(
                    this,
                    "당신의 태어난 해는 " + birthYear + "년입니다.",
                    Toast.LENGTH_LONG
            ).show();
        });
    }
}