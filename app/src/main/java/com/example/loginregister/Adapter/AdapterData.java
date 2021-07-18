package com.example.loginregister.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.API.APIRequestData;
import com.example.loginregister.API.RetroServer;
import com.example.loginregister.EditActivity;
import com.example.loginregister.Model.DataModel;
import com.example.loginregister.Model.ResponseModel;
import com.example.loginregister.R;
import com.example.loginregister.ui.gallery.GalleryFragment;
import com.google.android.material.transition.Hold;
import com.example.loginregister.ui.gallery.GalleryFragment;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{

    private Context ctx;
    private List<DataModel> listEasyIlmu;
    private List<DataModel> listUser;
    private String myRollNo;
    GalleryFragment GalleryFragment = new GalleryFragment();

    public AdapterData(Context ctx, List<DataModel> listEasyIlmu) {
        this.ctx = ctx;
        this.listEasyIlmu = listEasyIlmu;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(Layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        DataModel dm = listEasyIlmu.get(position);

        holder.tvId.setText(String.valueOf(dm.getRollNo()));
        holder.tvName.setText("Name: "+dm.getName());
        holder.tvMobNo.setText(String.valueOf("Mobile Number: "+dm.getMobNo()));
        holder.tvEmail.setText("Email: "+dm.getEmailId());

        //String test = String.valueOf(dm.getMobNo());
    }

    @Override
    public int getItemCount() {
        return listEasyIlmu.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvName,tvId,tvMobNo,tvEmail;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMobNo = itemView.findViewById(R.id.tv_mobile);
            tvEmail = itemView.findViewById(R.id.tv_email);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogMessage = new AlertDialog.Builder(ctx);
                    dialogMessage.setMessage("Which operation do you want");
                    dialogMessage.setCancelable(true);

                    myRollNo = tvId.getText().toString();

                    dialogMessage.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            /*Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    GalleryFragment.retrieveData();
                                }
                            }, 1000);*/

                        }
                    });
                    dialogMessage.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData();
                            dialog.dismiss();
                        }
                    });

                    dialogMessage.show();

                    return false;
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> deleteData = ardData.ardDeleteData(myRollNo);

            deleteData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();

                    Toast.makeText(ctx, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Connect to server fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> grabData = ardData.ardGetData(myRollNo);

            grabData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();
                    listUser = response.body().getData();

                    String varIdRollNo = listUser.get(0).getRollNo();
                    String varName = listUser.get(0).getName();
                    String varEmail = listUser.get(0).getEmailId();
                    int varMobno = listUser.get(0).getMobNo();
                    //BigInteger varMobNo = listUser.get(0).getMobNo();
                    //int varMobno = varMobNo.intValue();

                    Intent sendData = new Intent(ctx, EditActivity.class);
                    sendData.putExtra("xRoll",varIdRollNo);
                    sendData.putExtra("xName",varName);
                    sendData.putExtra("xEmail",varEmail);
                    sendData.putExtra( "xMobno",varMobno);

                    ctx.startActivity(sendData);

                    //Toast.makeText(ctx, "test: " +varEmail+varIdRollNo+varName+varMobNo, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ctx, "Done", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Connect to server fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
