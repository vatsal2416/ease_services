package services.ease.easeservices;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MechanicActivity2 extends AppCompatActivity {

    private Button btnPending, btnCompleted, btnCompleted2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnPending = findViewById(R.id.btnPendingRequests);
        btnCompleted = findViewById(R.id.btnCompletedRequests);
        btnCompleted2 = findViewById(R.id.btnComplete2);
        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String cardNo = intent.getStringExtra("mechanicCardNo");
                Intent intent1 = new Intent(MechanicActivity2.this,PendingRequestsActivity.class);
                intent1.putExtra("mechanicCardNo",cardNo);
                startActivity(intent1);
            }
        });

        btnCompleted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MechanicActivity2.this,CompletedActivity2.class);
                startActivity(intent);
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Intent intent1 = new Intent(MechanicActivity2.this,CompletedRequestsActivity.class);
                String cardNo = intent.getStringExtra("mechanicCardNo");
                intent1.putExtra("mechanicCardNo",cardNo);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MechanicActivity2.this, MechanicActivity.class));
        finish();
    }
}
