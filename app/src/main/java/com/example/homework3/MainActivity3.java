package com.example.homework3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        View root = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    bars.left,
                    bars.top,
                    bars.right,
                    bars.bottom
            );

            return insets;
        });

        CheckBox checkReading = findViewById(R.id.checkReading);
        CheckBox checkTravel = findViewById(R.id.checkTravel);
        CheckBox checkGame = findViewById(R.id.checkGame);

        Button btnSelect = findViewById(R.id.btnSelect);
        TextView txtResult = findViewById(R.id.txtResult);

        btnSelect.setOnClickListener(v -> {
            List<String> selectedHobbies = new ArrayList<>();

            if (checkReading.isChecked()) {
                selectedHobbies.add("독서");
            }

            if (checkTravel.isChecked()) {
                selectedHobbies.add("여행");
            }

            if (checkGame.isChecked()) {
                selectedHobbies.add("게임");
            }

            if (selectedHobbies.isEmpty()) {
                Toast.makeText(
                        this,
                        "취미를 선택해주세요.",
                        Toast.LENGTH_SHORT
                ).show();

                txtResult.setVisibility(View.GONE);
                return;
            }

            String result = "선택한 취미 : "
                    + String.join(", ", selectedHobbies);

            txtResult.setText(result);
            txtResult.setVisibility(View.VISIBLE);
        });
    }
}