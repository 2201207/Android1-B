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
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        EditText editId = findViewById(R.id.editId);
        EditText editPassword = findViewById(R.id.editPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String id = editId.getText().toString().trim();
            String password = editPassword.getText().toString();

            if (id.isEmpty() || password.trim().isEmpty()) {
                Toast.makeText(this, "데이터 입력 해주세요",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            WindowCompat.getInsetsController(getWindow(), root)
                    .hide(WindowInsetsCompat.Type.ime());

            Snackbar.make(root,
                    "아이디 : " + id + " 비밀번호 : " + password,
                    Snackbar.LENGTH_LONG).show();
        });
    }
}