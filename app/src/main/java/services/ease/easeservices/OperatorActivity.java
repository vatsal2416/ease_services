package services.ease.easeservices;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OperatorActivity extends AppCompatActivity {

    private Button submitBtn;
    private EditText editCardNo, editLineNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        submitBtn = findViewById(R.id.submitBtn);
        editCardNo = findViewById(R.id.editCardNo);
        editLineNo = findViewById(R.id.editLineNo);

        final MainActivity mainActivity = new MainActivity();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getToken = mainActivity.getFCMToken();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference referenceChild1 = reference.child("Operator :");
                DatabaseReference referenceChild2 = referenceChild1.child(editCardNo.getText().toString().trim());
                referenceChild2.child("CardNo").setValue(editCardNo.getText().toString().trim());
                referenceChild2.child("LineNo").setValue(editLineNo.getText().toString().trim());
                referenceChild2.child("FCM Token").setValue(getToken);
                Toast.makeText(getApplicationContext(),"Informed to Mechanic",Toast.LENGTH_SHORT).show();
                editCardNo.setText("");
                editLineNo.setText("");


                Intent intent = new Intent(OperatorActivity.this,MechanicActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(OperatorActivity.this,0,intent,0);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(OperatorActivity.this);
                mBuilder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
                mBuilder.setContentTitle("Mechanic Requested");
                mBuilder.setContentText("Card No : "+editCardNo.getText().toString().trim()
                        +"\nLine No : "+editLineNo.getText().toString().trim());
                mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setAutoCancel(true);
            }
        });
    }
}
