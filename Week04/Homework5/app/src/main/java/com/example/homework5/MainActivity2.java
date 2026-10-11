package com.example.homework5;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private SeekBar seekWeight;
    private SeekBar seekHeight;

    private TextView textWeight;
    private TextView textHeight;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (view, insets) -> {
                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    view.setPadding(
                            bars.left,
                            bars.top,
                            bars.right,
                            bars.bottom
                    );

                    return insets;
                }
        );

        seekWeight = findViewById(R.id.seekWeight);
        seekHeight = findViewById(R.id.seekHeight);

        textWeight = findViewById(R.id.textWeight);
        textHeight = findViewById(R.id.textHeight);
        textResult = findViewById(R.id.textResult);

        SeekBar.OnSeekBarChangeListener listener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {
                        calculateBmi();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };

        seekWeight.setOnSeekBarChangeListener(listener);
        seekHeight.setOnSeekBarChangeListener(listener);
    }

    private void calculateBmi() {
        double weight = 35.0 + seekWeight.getProgress() * 0.5;
        double height = 90.0 + seekHeight.getProgress() * 0.5;
        textWeight.setText(
                String.format(Locale.KOREA, "%.1f Kg", weight)
        );

        textHeight.setText(
                String.format(Locale.KOREA, "%.1f Cm", height)
        );

        double heightMeter = height / 100.0;
        double bmi = weight / (heightMeter * heightMeter);

        String result;

        if (bmi < 18.5) {
            result = "당신은 저체중 입니다!";
        } else if (bmi < 23.0) {
            result = "당신은 정상 체중 입니다!";
        } else if (bmi < 25.0) {
            result = "당신은 과체중 입니다!";
        } else {
            result = "당신은 비만 입니다!";
        }

        textResult.setText(
                String.format(
                        Locale.KOREA,
                        "BMI : %.2f\n%s",
                        bmi,
                        result
                )
        );
    }
}