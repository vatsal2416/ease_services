package services.ease.easeservices;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OperatorActivity extends AppCompatActivity {


    private EditText editCardNo, editLineNo, editMachineNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button submitBtn;
        submitBtn = findViewById(R.id.submitBtn);
        editCardNo = findViewById(R.id.editCardNo);
        editLineNo = findViewById(R.id.editLineNo);
        editMachineNo = findViewById(R.id.editMachineNo);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((checkOperator(editCardNo.getText().toString())) && checkMachineNumber(editMachineNo.getText().toString())){
                    if((editCardNo.getText().toString().trim()).equals("") || (editLineNo.getText().toString().trim()).equals("")
                            || (editMachineNo.getText().toString().trim()).equals("")){
                        Toast.makeText(getApplicationContext(),"Card Number or Machine number cannot be empty!",Toast.LENGTH_SHORT).show();
                    }else{
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference referenceChild1 = reference.child("Operator :");
                        DatabaseReference referenceChild2 = referenceChild1.child(editCardNo.getText().toString().trim());
                        referenceChild2.child("CardNo").setValue(editCardNo.getText().toString());
                        referenceChild2.child("LineNo").setValue(editLineNo.getText().toString().trim());
                        referenceChild2.child("MachineNo").setValue(editMachineNo.getText().toString().trim());
                        Toast.makeText(getApplicationContext(),"Informed to Mechanic",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OperatorActivity.this,MainActivity.class));
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Operator Number or Machine Number!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    protected boolean checkOperator(String operatorNo){
        boolean validOperator;

        validOperator = operatorNo.equals("814171") || operatorNo.equals("241949") || operatorNo.equals("200967") || operatorNo.equals("246640") || operatorNo.equals("243130")
                || operatorNo.equals("659494") || operatorNo.equals("600217") || operatorNo.equals("248133") || operatorNo.equals("248133") || operatorNo.equals("248126");
        return validOperator;
    }

    protected boolean checkMachineNumber(String machineNo){
        boolean validMachine;

        validMachine = machineNo.equals("F2842") || machineNo.equals("F1346") || machineNo.equals("B2351") || machineNo.equals("H4287")
                || machineNo.equals("H3992") || machineNo.equals("A3215") || machineNo.equals("F4276") || machineNo.equals("A3124")
                || machineNo.equals("F4942") || machineNo.equals("F3156");

        return validMachine;
    }
}
