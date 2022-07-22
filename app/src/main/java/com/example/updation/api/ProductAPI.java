package com.example.updation.api;

import com.example.updation.entities.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductAPI {

    @PUT("product/save")
    Call<Product> save(@Body Product product);

}
