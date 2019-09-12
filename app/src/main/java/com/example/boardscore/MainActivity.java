package com.example.boardscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JadwalAdapter adapter;
    private ArrayList<Schedules_Model> dataList;
    public static final String GOOGLE_ACCOUNT = "google_account";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data=response.getJSONArray("events");
                            for (int i=0;i<data.length() ;i++){
                                Schedules_Model SchedulesModel=new Schedules_Model();
                                JSONObject json=data.getJSONObject(i);
                                SchedulesModel.setStrHomeTeam(json.getString("strHomeTeam"));
                                SchedulesModel.setStrAwayTeam(json.getString("strAwayTeam"));
                                SchedulesModel.setStrDate(json.getString("strDate"));
                                dataList.add(SchedulesModel);
                            }
                            adapter= new JadwalAdapter(dataList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void onComposeAction(MenuItem mi) {
        Intent menu =new Intent(this, About.class);
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        menu.putExtra(About.GOOGLE_ACCOUNT, googleSignInAccount);
        String nama = getIntent().getStringExtra("data1");
        String email = getIntent().getStringExtra("data2");
        String image = getIntent().getStringExtra("data3");
        System.out.println(nama + "yers ");
        menu.putExtra("data1", nama);
        menu.putExtra("data2", email);
        menu.putExtra("data3", image);


        startActivity(menu);
    }
   /* @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }*/
    }

