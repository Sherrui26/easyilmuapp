package com.example.loginregister.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.API.APIRequestData;
import com.example.loginregister.API.RetroServer;
import com.example.loginregister.Adapter.AdapterData2;
import com.example.loginregister.Model.DataModel2;
import com.example.loginregister.Model.MyModel2;
import com.example.loginregister.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    public void onStart(){
        super.onStart();

        System.exit(0);
    }
    /*private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel2> listData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        rvData = view.findViewById(R.id.rv_book);
        lmData = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        retrieveData();
        return view;
    }

    public void retrieveData(){

        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<MyModel2> showData = ardData.ardRetrieveDataBook();

        showData.enqueue(new Callback<MyModel2>() {
            @Override
            public void onResponse(Call<MyModel2> call, Response<MyModel2> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                Toast.makeText(getActivity(), "Code: "+code+"| Message: "+message, Toast.LENGTH_SHORT).show();
                listData = response.body().getData();

                adData = new AdapterData2(getActivity(),listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MyModel2> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to Retrieve Data", Toast.LENGTH_SHORT).show();
            }
        });

    }*/
}