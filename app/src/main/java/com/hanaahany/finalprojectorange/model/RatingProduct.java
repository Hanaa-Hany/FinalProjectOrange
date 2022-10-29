package com.hanaahany.finalprojectorange.model;

public class RatingProduct {

    private double rate;
    private int count;

    public RatingProduct(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
