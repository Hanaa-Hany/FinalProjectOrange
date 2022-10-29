package com.hanaahany.finalprojectorange.model;

import java.util.List;

public class ProductModel {
    private int total;
    private int skip;
    private int limit;
    private List<ProductModelDetails> products;

    public ProductModel(int total, int skip, int limit, List<ProductModelDetails> products) {
        this.total = total;
        this.skip = skip;
        this.limit = limit;
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<ProductModelDetails> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModelDetails> products) {
        this.products = products;
    }
}
