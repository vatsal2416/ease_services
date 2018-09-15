package services.ease.easeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private Button operatorBtn, mechanicBtn, btnGetFCMToken;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operatorBtn = findViewById(R.id.operatorBtn);
        mechanicBtn = findViewById(R.id.mechanicBtn);
        btnGetFCMToken = findViewById(R.id.btnFCMtoken);

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

        btnGetFCMToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token = FirebaseInstanceId.getInstance().getToken();
                Toast.makeText(getApplicationContext(),"FCM Token : "+token,Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected String getFCMToken(){
        return token;
    }
}
