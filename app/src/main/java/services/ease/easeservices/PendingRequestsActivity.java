package services.ease.easeservices;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PendingRequestsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    String item;
    AlertDialog.Builder atlDial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_requests);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        listView = findViewById(R.id.listPending);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Operator :");
        final List<String> list = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                for(DataSnapshot data1 : data){
                    String msg = "";
                    msg = data1.getValue().toString().trim();
                    list.add(msg);
                }
                ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                        (PendingRequestsActivity.this,android.R.layout.simple_list_item_1,list);
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
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
        Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
        item = adapter.getItemAtPosition(position).toString();
        atlDial = new AlertDialog.Builder(PendingRequestsActivity.this);
        atlDial.setMessage("Do you want to accept this Request?" + "\n"+item).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        insert(item);
                        Toast.makeText(getApplicationContext(),"Repair request logged.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PendingRequestsActivity.this,MechanicActivity.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Rejected by Mechanic",Toast.LENGTH_SHORT).show();
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
        Intent intent = getIntent();
        String cardNo = intent.getStringExtra("mechanicCardNo").trim();
        if(!cardNo.equals("")){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference reference1 = reference.child("Accepted Requests");
            DatabaseReference reference2 = reference1.child(cardNo);
            String insertData = data+"\nStart Time : "+getCurrentTime();
            reference2.child("Operator Info").setValue("Mechanic : "+cardNo+insertData);
            }else{
            Toast.makeText(getApplicationContext(),"Please restart Application",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PendingRequestsActivity.this, MechanicActivity.class));
        finish();
    }
}