package com.example.boardscore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.SchedulesViewHolder>{
    private ArrayList<Schedules_Model >dataList;

    JadwalAdapter(ArrayList<Schedules_Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public JadwalAdapter.SchedulesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.soccer_list, parent, false);
        return new SchedulesViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.SchedulesViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getStrHomeTeam());
        holder.txtnw.setText(dataList.get(position).getStrAwayTeam());
        holder.txtdate.setText(dataList.get(position).getStrDate());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    class SchedulesViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName ,txtnw,txtdate;

        SchedulesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_nama_home);
            txtnw = (TextView) itemView.findViewById(R.id.txt_name_away_);
            txtdate = (TextView) itemView.findViewById(R.id.txt_Date);
        }
    }
}
