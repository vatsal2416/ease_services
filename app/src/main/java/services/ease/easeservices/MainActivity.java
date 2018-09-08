package services.ease.easeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button operatorBtn, mechanicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operatorBtn = findViewById(R.id.operatorBtn);
        mechanicBtn = findViewById(R.id.mechanicBtn);

        operatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OperatorActivity.class);
                startActivity(intent);
            }
        });

        mechanicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MechanicActivity.class);
                startActivity(intent);
            }
        });
    }
}
