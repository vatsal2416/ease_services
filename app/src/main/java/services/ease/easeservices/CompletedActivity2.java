package services.ease.easeservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompletedActivity2 extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed2);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Completed Requests");
        final List<String> list = new ArrayList<>();
        listView = findViewById(R.id.listView);

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
                        (CompletedActivity2.this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(myadapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Cancel
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CompletedActivity2.this, MechanicActivity.class));
        finish();
    }


}
