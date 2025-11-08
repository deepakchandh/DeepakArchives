package com.java.concepts.oops.v2;

import java.time.LocalDate;

public class Coupon {

    private String categoryName;
    private String couponName;
    private LocalDate dateModified;

    public Coupon(String categoryName, String couponName, String dateModified) {
        this.categoryName = categoryName;
        this.couponName = couponName;
        this.dateModified = LocalDate.parse(dateModified);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCouponName() {
        return couponName;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }
}
