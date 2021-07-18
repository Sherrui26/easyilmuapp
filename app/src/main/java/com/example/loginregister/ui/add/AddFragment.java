package com.example.loginregister.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginregister.API.APIRequestData;
import com.example.loginregister.API.RetroServer;
import com.example.loginregister.Model.MyModel2;
import com.example.loginregister.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {

    private EditText ebookName,etPublisher,etAuthor,etAvailability,etYear;
    private Button submit;
    private String Title,Publisher,Year,Availability;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        ebookName = (EditText) view.findViewById(R.id.bookName2);
        etPublisher = (EditText)view.findViewById(R.id.bookPublisher2);
        etAuthor = (EditText)view.findViewById(R.id.bookAuthor2);
        etYear = (EditText)view.findViewById(R.id.bookYear2);
        etAvailability =(EditText) view.findViewById(R.id.bookCopies2);
        submit = (Button) view.findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title = ebookName.getText().toString();
                Publisher = etPublisher.getText().toString();
                Year = etYear.getText().toString();
                Availability = etAvailability.getText().toString();

                if(Title.trim().equals("")){
                    ebookName.setError("Please enter book's name");
                }
                else if(Publisher.trim().equals("")){
                    etPublisher.setError("Please enter publisher");
                }
                else if(Year.trim().equals("")){
                    etYear.setError("Please enter year");
                }
                else if(Availability.trim().equals("")){
                    etAvailability.setError("Please enter number of copies");
                }
                else{
                    createData();
                    ebookName.getText().clear();
                    etAuthor.getText().clear();
                    etPublisher.getText().clear();
                    etYear.getText().clear();
                    etAvailability.getText().clear();
                }

            }
        });


        return view;
    }

    private void createData(){
        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<MyModel2> storeData = ardData.ardCreateData(Title,Publisher,Year,Availability);

        storeData.enqueue(new Callback<MyModel2>() {
            @Override
            public void onResponse(Call<MyModel2> call, Response<MyModel2> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                Toast.makeText(getActivity(), "Book Added Successfully!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MyModel2> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private AddViewModel addViewModel;
    private FragmentAddBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAdd;
        addViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
}