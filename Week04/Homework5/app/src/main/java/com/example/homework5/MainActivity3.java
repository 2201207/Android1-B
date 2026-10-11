package com.example.homework5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (view, insets) -> {
                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    view.setPadding(
                            bars.left, bars.top, bars.right, bars.bottom
                    );

                    return insets;
                }
        );

        TextView textCelsius = findViewById(R.id.textCelsius);
        TextView textFahrenheit = findViewById(R.id.textFahrenheit);
        TextView textResult = findViewById(R.id.textResult);

        SeekBar seekCelsius = findViewById(R.id.seekCelsius);
        SeekBar seekFahrenheit = findViewById(R.id.seekFahrenheit);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnReset = findViewById(R.id.btnReset);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            seekCelsius.setEnabled(checkedId == R.id.radioCelsius);
            seekFahrenheit.setEnabled(checkedId == R.id.radioFahrenheit);
            textResult.setText("");
        });

        seekCelsius.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        double celsius = progress - 100;

                        textCelsius.setText(
                                String.format(
                                        Locale.KOREA,
                                        "섭씨온도(%.1f ℃)",
                                        celsius
                                )
                        );

                        textResult.setText("");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        seekFahrenheit.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        double fahrenheit = progress - 100;

                        textFahrenheit.setText(
                                String.format(
                                        Locale.KOREA,
                                        "화씨온도(%.1f ℉)",
                                        fahrenheit
                                )
                        );

                        textResult.setText("");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        btnConvert.setOnClickListener(view -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(
                        this, "선택해주세요", Toast.LENGTH_SHORT
                ).show();
                return;
            }

            double celsius;
            double fahrenheit;

            if (selectedId == R.id.radioCelsius) {
                celsius = seekCelsius.getProgress() - 100;
                fahrenheit = celsius * 1.8 + 32;
            } else {
                fahrenheit = seekFahrenheit.getProgress() - 100;
                celsius = (fahrenheit - 32) / 1.8;
            }

            textResult.setText(
                    String.format(
                            Locale.KOREA,
                            "섭씨온도 : %.2f ℃, 화씨온도 : %.2f ℉",
                            celsius,
                            fahrenheit
                    )
            );
        });

        btnReset.setOnClickListener(view -> {
            radioGroup.clearCheck();

            seekCelsius.setEnabled(false);
            seekFahrenheit.setEnabled(false);

            seekCelsius.setProgress(100);
            seekFahrenheit.setProgress(100);

            textCelsius.setText("섭씨온도(0.0 ℃)");
            textFahrenheit.setText("화씨온도(0.0 ℉)");
            textResult.setText("");
        });
    }
}