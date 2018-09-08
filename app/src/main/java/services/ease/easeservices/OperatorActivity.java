package services.ease.easeservices;

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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference referenceChild = reference.child("Operator");
                referenceChild.child("CardNo").setValue(editCardNo.getText().toString().trim());
                referenceChild.child("LineNo").setValue(editLineNo.getText().toString().trim());

                Toast.makeText(getApplicationContext(),"Informed to Mechanic",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
