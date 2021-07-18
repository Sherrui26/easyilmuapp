package com.example.loginregister.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Model.DataModel2;
import com.example.loginregister.R;
import com.google.android.material.transition.Hold;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterData2 extends RecyclerView.Adapter<AdapterData2.HolderData>{

    private Context ctx;
    private List<DataModel2> listEasyIlmu;

    public AdapterData2(Context ctx, List<DataModel2> listEasyIlmu) {
        this.ctx = ctx;
        this.listEasyIlmu = listEasyIlmu;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_book,parent,false);
        AdapterData2.HolderData holder = new AdapterData2.HolderData(Layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        DataModel2 dm = listEasyIlmu.get(position);

        holder.tvBookId.setText(String.valueOf("Book Id: "+dm.getBookId()));
        holder.tvBookName.setText(dm.getTitle());
        holder.tvPublisher.setText(dm.getPublisher());
        holder.tvYear.setText(dm.getYear());
        holder.tvAvailability.setText(String.valueOf("Availability: "+dm.getAvailability()));

    }

    @Override
    public int getItemCount() {
        return listEasyIlmu.size();
    }


    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvBookId,tvBookName,tvPublisher,tvYear,tvAvailability;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            tvBookId = itemView.findViewById(R.id.tv_bookId);
            tvBookName = itemView.findViewById(R.id.tv_title);
            tvPublisher = itemView.findViewById(R.id.tv_publisher);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvAvailability = itemView.findViewById(R.id.tv_availability);
        }
    }

}
