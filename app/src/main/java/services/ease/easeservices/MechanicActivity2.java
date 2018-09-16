package services.ease.easeservices;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MechanicActivity2 extends AppCompatActivity {

    private Button btnPending, btnCompleted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnPending = findViewById(R.id.btnPendingRequests);
        btnCompleted = findViewById(R.id.btnCompletedRequests);

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

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MechanicActivity2.this,CompletedRequestsActivity.class));
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
