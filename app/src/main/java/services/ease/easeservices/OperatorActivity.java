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
import com.google.firebase.iid.FirebaseInstanceId;

public class OperatorActivity extends AppCompatActivity {

    private Button submitBtn;
    private EditText editCardNo, editLineNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        submitBtn = findViewById(R.id.submitBtn);
        editCardNo = findViewById(R.id.editCardNo);
        editLineNo = findViewById(R.id.editLineNo);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOperator(editCardNo.getText().toString())){
                    if((editCardNo.getText().toString().trim()).equals("") || (editLineNo.getText().toString().trim()).equals("")){
                        Toast.makeText(getApplicationContext(),"Card No. or Line No. cannot be Empty!",Toast.LENGTH_SHORT).show();
                    }else{
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference referenceChild1 = reference.child("Operator :");
                        DatabaseReference referenceChild2 = referenceChild1.child(editCardNo.getText().toString().trim());
                        referenceChild2.child("CardNo").setValue(editCardNo.getText().toString());
                        referenceChild2.child("LineNo").setValue(editLineNo.getText().toString().trim());
                        Toast.makeText(getApplicationContext(),"Informed to Mechanic",Toast.LENGTH_SHORT).show();
                        editCardNo.setText("");
                        editLineNo.setText("");
                        startActivity(new Intent(OperatorActivity.this,MainActivity.class));
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Operator Number",Toast.LENGTH_SHORT).show();
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
        boolean validOperator = false;

        if(operatorNo.equals("814171") || operatorNo.equals("241949") || operatorNo.equals("200967") || operatorNo.equals("246640") || operatorNo.equals("243130")
                || operatorNo.equals("659494") || operatorNo.equals("600217") || operatorNo.equals("248133") || operatorNo.equals("248133") || operatorNo.equals("248126")){
            validOperator = true;
        }else{
            validOperator = false;
        }
        return validOperator;
    }
}
