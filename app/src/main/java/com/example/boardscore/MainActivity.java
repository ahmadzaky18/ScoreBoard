package com.example.boardscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JadwalAdapter adapter;
    private ArrayList<Schedules_Model> dataList;
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

    }

