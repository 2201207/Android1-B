package com.example.homework5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean selected1 = false;
    private boolean selected2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        TextView textNumber1 = findViewById(R.id.textNumber1);
        TextView textNumber2 = findViewById(R.id.textNumber2);
        TextView textResult = findViewById(R.id.textResult);

        SeekBar seekNumber1 = findViewById(R.id.seekNumber1);
        SeekBar seekNumber2 = findViewById(R.id.seekNumber2);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        seekNumber1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        textNumber1.setText(
                                "자연수 입력(" + (progress + 1) + ")"
                        );

                        if (fromUser) {
                            selected1 = true;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        selected1 = true;
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        seekNumber2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        textNumber2.setText(
                                "자연수 입력(" + (progress + 1) + ")"
                        );

                        if (fromUser) {
                            selected2 = true;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        selected2 = true;
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        btnCalculate.setOnClickListener(view -> {
            if (!selected1 || !selected2) {
                Toast.makeText(
                        this, "입력 바람", Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int number1 = seekNumber1.getProgress() + 1;
            int number2 = seekNumber2.getProgress() + 1;

            int gcd = getGcd(number1, number2);
            int lcm = number1 * number2 / gcd;

            textResult.setText(
                    number1 + ", " + number2
                            + "의 GCD(최대공약수)는 " + gcd + "입니다.\n"
                            + number1 + ", " + number2
                            + "의 LCM(최소공배수)는 " + lcm + "입니다."
            );
        });
    }

    private int getGcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}