package com.hanaahany.finalprojectorange.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("products/categories")
    Call<List<String>>getAllCategories();

    @GET("products")
    Call<ProductModel>getAllProducts();

    @GET("products")
    Call<ProductModel>getLimitProducts(@Query("limit") int limit);

    @GET("products")
    Call<ProductModel>getFilterProducts(@Query("sort") String limit);

    @GET("products/{id}")
    Call<ProductModelDetails>getSingleProduct(@Path("id") int id);
    @GET("products/search")
    Call<ProductModel>searchProduct(@Query("q") String searchName);

    @GET("products/category/{categoryName}")
    Call<ProductModel>getProductsOfCategory(@Path("categoryName") String categoryName);

//    @PUT("carts/20")
//    Call< >addToCart(@Path("id") int id, @Body ProductAddToCartRaw productAddToCart);
}
