package com.example.loginregister.ui.home;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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

public class HomeFragment extends Fragment {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private APIRequestData apiInterface;
    private AdapterData adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvData = view.findViewById(R.id.rv_user);
        srlData = view.findViewById(R.id.swl_data);
        pbData = view.findViewById(R.id.pb_data);

        lmData = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        //retrieveData();
        fetchContact("users", "");

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                //retrieveData();
                fetchContact("users", "");
                srlData.setRefreshing(false);
            }
        });
        setHasOptionsMenu(true);


        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sidebar, menu);
        //MenuItem searchItem = menu.findItem(R.id.search);
        //SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String query) {
                fetchContact("users", query);
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                fetchContact("users", newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


//******************************************
    public void fetchContact(String type, String key){

        apiInterface = RetroServer.connectRetrofit().create(APIRequestData.class);

        Call<List<DataModel>> call = apiInterface.ardGetContact(type, key);
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                //progressBar.setVisibility(View.GONE);
                listData = response.body();
                adapter = new AdapterData(getActivity(),listData);  //*************
                rvData.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchContact("users", "");
        //retrieveData();
    }

    public void retrieveData(){
        pbData.setVisibility(View.VISIBLE);

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
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to Retrieve Data", Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        rvData = inflate.findViewById(R.id.rv_user);
        lmData = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
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

        rvData = view.findViewById(R.id.rv_user);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


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
