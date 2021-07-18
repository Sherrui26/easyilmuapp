package com.example.loginregister.API;

import com.example.loginregister.Model.DataModel;
import com.example.loginregister.Model.ResponseModel;
import com.example.loginregister.Model.MyModel2;

import java.math.BigInteger;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("retrieve_user.php")
    Call<ResponseModel> ardRetrieveData();

    @GET("retrieve_books.php")
    Call<MyModel2> ardRetrieveDataBook();

    @FormUrlEncoded
    @POST("create.php")
    Call<MyModel2> ardCreateData(

            @Field("Title") String Title,
            @Field("Publisher") String Publisher,
            @Field("Year") String Year,
            @Field("Availability") String Availability

    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("RollNo") String RollNo
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("RollNo") String RollNo
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(

            @Field("RollNo") String RollNo,
            @Field("Name") String Name,
            @Field("EmailId") String EmailId,
            @Field("MobNo") int MobNo

    );

    @GET("getcontacts.php")
    Call<List<DataModel>> ardGetContact(
                    @Query("item_type") String item_type,
                    @Query("key") String keyword
            );
}
