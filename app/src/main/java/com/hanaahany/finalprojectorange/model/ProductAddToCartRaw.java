package com.hanaahany.finalprojectorange.model;

import java.util.List;

public class ProductAddToCartRaw {

    List<ProductRaw> products;

    public ProductAddToCartRaw(List<ProductRaw> products) {
        this.products = products;
    }

    public List<ProductRaw> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRaw> products) {
        this.products = products;
    }

    private class ProductRaw {
        private int id;
        private int quantity;

        public ProductRaw(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
