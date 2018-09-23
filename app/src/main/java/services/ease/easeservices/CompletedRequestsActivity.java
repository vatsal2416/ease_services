package services.ease.easeservices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompletedRequestsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    AlertDialog.Builder atlDial;
    String item;
    String mechanicCardNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_requests);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        listView = findViewById(R.id.listCompleted);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accepted Requests");
        final List<String> list = new ArrayList<>();

        Intent getIntent = getIntent();
        mechanicCardNo = getIntent.getStringExtra("mechanicCardNo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                for(DataSnapshot data1 : data){
                    String msg = "";
                    msg = data1.getValue().toString().trim();
                    list.add(msg);
                }
                ArrayAdapter<String> myadapter = new ArrayAdapter<>
                        (CompletedRequestsActivity.this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(myadapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Cancel
            }
        });

        listView.setOnItemClickListener(this);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CompletedRequestsActivity.this, MechanicActivity.class));
        finish();

    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
        item = adapter.getItemAtPosition(position).toString();
        atlDial = new AlertDialog.Builder(CompletedRequestsActivity.this);
        atlDial.setMessage("This machine issue is Resolved? " + "\n").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insert(item);
                        Toast.makeText(getApplicationContext(),"Machine Issue resolved.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CompletedRequestsActivity.this,MechanicActivity.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Repairing still in progress.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CompletedRequestsActivity.this,MechanicActivity.class));
            }
        });

        AlertDialog alert = atlDial.create();
        alert.show();
    }

    public String getCurrentTime(){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String dateToStr = format.format(today);
        return dateToStr;
    }

    private void insert(String data) throws NullPointerException{
        if(!mechanicCardNo.equals("")){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference reference1 = reference.child("Completed Requests");
            DatabaseReference reference2 = reference1.child(mechanicCardNo);
            String insertData = data+"\nEnd Time : "+getCurrentTime();
            reference2.child("Operator Info").setValue("Mechanic : "+mechanicCardNo+insertData);
          }else{
            Toast.makeText(getApplicationContext(),"Please restart Application",Toast.LENGTH_SHORT).show();
        }
    }
}
