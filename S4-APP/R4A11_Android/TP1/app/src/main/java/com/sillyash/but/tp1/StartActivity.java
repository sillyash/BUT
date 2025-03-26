package com.sillyash.but.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {
    protected EditText inputTableNum;
    protected Button btnTableNum;
    protected int tableNum;
    public static final String TABLE = "tableNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("CREATE", this.getClass().getName());

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.inputTableNum = findViewById(R.id.inputTableNumber);
        this.btnTableNum = findViewById(R.id.btnTableNumber);
        this.setTableButtonListener();
    }

    protected void setTableButtonListener() {
        this.btnTableNum.setOnClickListener(v -> {
            String value = this.inputTableNum.getText().toString();

            if (value.isEmpty()) return;
            int tbl = Integer.parseInt(value);

            if ( ! ServerTools.isTableNumberOK(tbl)) {
                Toast.makeText(this, R.string.tableNumFormatError, Toast.LENGTH_SHORT).show();
                return;
            }

            this.tableNum = tbl;
            Intent i = this.saveTableNumber();
            this.goToMainActivity(i);
        });
    }

    protected static boolean tableNumberOK(int n) {
        return (n > 0 && n < 100);
    }

    protected Intent saveTableNumber() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra(TABLE, this.tableNum);
        return intent;
    }

    protected void goToMainActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() { super.onStart(); Log.i("START", this.getClass().getName()); }
    @Override
    protected void onRestart() { super.onRestart(); Log.i("RESTART", this.getClass().getName()); }
    @Override
    protected void onResume() { super.onResume(); Log.i("RESUME", this.getClass().getName()); }
    @Override
    protected void onPause() { super.onPause(); Log.i("PAUSE", this.getClass().getName()); }
    @Override
    protected void onStop() { super.onStop(); Log.i("STOP", this.getClass().getName()); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.i("DESTROY", this.getClass().getName()); }
}
