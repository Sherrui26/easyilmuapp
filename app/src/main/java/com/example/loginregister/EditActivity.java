package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.API.APIRequestData;
import com.example.loginregister.API.RetroServer;
import com.example.loginregister.Model.DataModel;
import com.example.loginregister.Model.MyModel2;
import com.example.loginregister.Model.ResponseModel;
import com.example.loginregister.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    private String xRoll,xName,xEmail;
    private int xMobno;
    private EditText etRoll,etEmail,etMobno,etName;
    private Button btnEdit;
    private String yRoll,yEmail,yName;

    DataModel dm = new DataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent receive = getIntent();
        xMobno = receive.getIntExtra("xMobno",-1);
        xRoll = receive.getStringExtra("xRoll");
        xName = receive.getStringExtra("xName");
        xEmail = receive.getStringExtra("xEmail");

        etRoll = findViewById(R.id.EditRoll);
        etEmail = findViewById(R.id.EditEmail);
        //etMobno = findViewById(R.id.EditMobNo);
        etName = findViewById(R.id.EditName);
        btnEdit = findViewById(R.id.btn_change);

        etName.setText(xName);
        etRoll.setText(xRoll);
        //etMobno.setText(xMobno);
        etEmail.setText(xEmail);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yRoll = etRoll.getText().toString();
                yEmail = etEmail.getText().toString();
                yName = etName.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> updateData = ardData.ardUpdateData(yRoll,yName,yEmail,xMobno);

        updateData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                Toast.makeText(EditActivity.this, "Done", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Fail to Change ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}