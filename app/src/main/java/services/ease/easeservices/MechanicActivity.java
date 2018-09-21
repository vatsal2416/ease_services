package services.ease.easeservices;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.List;

public class MechanicActivity extends AppCompatActivity {

    private Button btnMechanicCardNo;
    private DatabaseReference database1;
    private EditText editMechanicCard;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        editMechanicCard = findViewById(R.id.editMechanicCard);
        btnMechanicCardNo = findViewById(R.id.btnMechanicCard);
        btnMechanicCardNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMechanic(editMechanicCard.getText().toString().trim())){
                    if(!(editMechanicCard.getText().toString().trim()).equals("")){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference referenceChild1 = reference.child("Mechanic");
                        referenceChild1.child(editMechanicCard.getText().toString().trim()).setValue(editMechanicCard.getText().toString().trim());
                        Intent intent = new Intent(MechanicActivity.this,MechanicActivity2.class);
                        intent.putExtra("mechanicCardNo",editMechanicCard.getText().toString().trim());
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"CardNo cannot be empty!",Toast.LENGTH_SHORT);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Mechannic CardNo!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MechanicActivity.this, MainActivity.class));
        finish();
    }

    protected boolean checkMechanic(String mechanicNo){
        boolean validMechanic = false;

        if(mechanicNo.equals("563341") || mechanicNo.equals("732145")){
            validMechanic = true;
        }else{
            validMechanic = false;
        }

        return validMechanic;
    }
}
