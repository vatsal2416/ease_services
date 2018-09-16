package services.ease.easeservices;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MechanicActivity extends AppCompatActivity {

    private Button btnGetData;
    private DatabaseReference database1,database2;
    int i,j;
    String[] operatorCardNo, operatorLineNo,operatorCompleted,operatorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        database1 = FirebaseDatabase.getInstance().getReference("Operator :");

        final List<String> list = new ArrayList<>();
        final String[] childrenData = new String[100];
        i = 0;
        j = 0;
        operatorCardNo = new String[100];
        operatorLineNo = new String[100];
        operatorCompleted = new String[100];
        System.out.println("Data From Snapshot : ");
        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                for(DataSnapshot data1 : data){
                    String msg = "";
                    operatorName = new String[100];
                    operatorName[i] = (String)data1.child("CardNo").getValue();
                    //Toast.makeText(getApplicationContext(),"OperatorName: "+operatorName,Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            int operatorCount = 0;
            database2 = database1.child(operatorName[operatorCount]).child("completed");
            database2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                        for (DataSnapshot data1 : data) {
                            Toast.makeText(getApplicationContext(), "Completed: " + data1.child(operatorName[i]).child("completed").getValue(), Toast.LENGTH_SHORT).show();
                            boolean condition = (data1.child(operatorName[i]).child("completed").getValue()) == ("false");
                            i++;
                            if (condition) {
                                System.out.println("Inside 'completed' condition");
                                operatorCardNo[j] = data1.child("CardNo").getValue().toString().trim();
                                operatorLineNo[j] = data1.child("LineNo").getValue().toString().trim();
                                operatorCompleted[j] = data1.child("completed").getValue().toString().trim();
                                Toast.makeText(getApplicationContext(), "\nOperator : \nCardNo: " + operatorCardNo[j] + "\tLineNo: " + operatorLineNo[j] + "\tCompleted: " + operatorCompleted[j], Toast.LENGTH_SHORT).show();
                                j++;
                            } else {
                                Toast.makeText(getApplicationContext(), "No Operator Data found", Toast.LENGTH_SHORT).show();
                            }
                            System.out.println("Null Operator found");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Exception occured : " + e);
                        e.printStackTrace();
                        System.out.println("Operator : " + operatorName[i]);
                    }

//                    Toast.makeText(getApplicationContext(),"Operator : "+data1.child("CardNo").getValue(),Toast.LENGTH_SHORT).show();

//                    list.add(msg);
//                    i++;
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        btnGetData = findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOperatorData();
                startActivity(new Intent(MechanicActivity.this,ViewDataActivity.class));
            }
        });
    }

    protected void getOperatorData(){
        database1 = FirebaseDatabase.getInstance().getReference("Operator :");
        String data = database1.child("completed").getKey();
        Toast.makeText(getApplicationContext(),"Key : "+data, Toast.LENGTH_SHORT).show();
    }
}
