package com.example.loginregister.test.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.API.APIRequestData;
import com.example.loginregister.API.RetroServer;
import com.example.loginregister.Adapter.AdapterData;
import com.example.loginregister.Model.DataModel;
import com.example.loginregister.Model.ResponseModel;
import com.example.loginregister.R;
import com.example.loginregister.databinding.FragmentGalleryBinding;
import com.example.loginregister.databinding.FragmentHomeBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.loginregister.R;
import com.example.loginregister.ui.gallery.GalleryViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testfragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataModel> listData = new ArrayList<>();
    private AdapterData adapter;
    private APIRequestData apiInterface;
    private ProgressBar progressBar;
    private SwipeRefreshLayout srlData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_user);
        srlData = view.findViewById(R.id.swl_data);
        progressBar = view.findViewById(R.id.pb_data);

        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //retrieveData();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        retrieveData();
    }

    public void retrieveData(){
        progressBar.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> showData = ardData.ardRetrieveData();

        showData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                Toast.makeText(getActivity(), /*"Code: "+code+"| Message: "+message*/"Users List", Toast.LENGTH_SHORT).show();
                listData = response.body().getData();

                adData = new AdapterData(getActivity(),listData);
                recyclerView.setAdapter(adData);
                adData.notifyDataSetChanged();

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to Retrieve Data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = inflate.findViewById(R.id.rv_user);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        retrieveData();

        return inflate;
    }*/

      /*@Override
    public void onStart() {
        super.onStart();

        callUser();
    }



/*public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //ImageView imageView = (ImageView) view.findViewById(R.id.my_image);

        recyclerView = view.findViewById(R.id.rv_user);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


        return view;
    }*/


    /*private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
