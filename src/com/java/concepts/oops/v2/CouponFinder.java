package com.java.concepts.oops.v2;

import java.time.LocalDate;
import java.util.*;

public class CouponFinder {

    private final Map<String, List<Coupon>> couponsByCategory = new HashMap<>();
    private final Map<String, String> parentMap = new HashMap<>();

    public CouponFinder(List<Coupon> coupons, List<Category> categories) {
        loadCoupons(coupons);
        loadCategories(categories);
    }

    private void loadCoupons(List<Coupon> coupons) {
        LocalDate today = LocalDate.now();

        for (Coupon coupon : coupons) {
            // Skip coupons modified in the future
            if (coupon.getDateModified().isAfter(today)) continue;

            couponsByCategory
                    .computeIfAbsent(coupon.getCategoryName(), k -> new ArrayList<>())
                    .add(coupon);
            /*List<Coupon> existingCoupons = couponsByCategory.get(coupon.getCategoryName());
            if (existingCoupons == null) {
                existingCoupons = new ArrayList<>();
                couponsByCategory.put(coupon.getCategoryName(), existingCoupons);
            }
            existingCoupons.add(coupon);*/
        }
    }

    public String findBestCoupon(String categoryName) {
        String current = categoryName;

        while (current != null) {
            List<Coupon> coupons = couponsByCategory.get(current);
            if (coupons != null && !coupons.isEmpty()) {
                // Find coupon with most recent DateModified
                Coupon best = Collections.max(coupons, Comparator.comparing(Coupon::getDateModified));
                return best.getCouponName();
            }
            current = parentMap.get(current); // move up the hierarchy
        }

        return null;
    }



    private void loadCategories(List<Category> categories){
        for(Category category: categories){
            parentMap.put(category.getCategoryName(), category.getParentName());
        }
    }

    public static void main(String[] args) {
        List<Coupon> coupons = List.of(
                new Coupon("Comforter Sets", "Comforters Sale", "2020-01-01"),
                new Coupon("Comforter Sets", "Cozy Comforter Coupon", "2020-01-01"),
                new Coupon("Bedding", "Best Bedding Bargains", "2019-01-01"),
                new Coupon("Bedding", "Savings on Bedding", "2019-01-01"),
                new Coupon("Bed & Bath", "Low price for Bed & Bath", "2018-01-01"),
                new Coupon("Bed & Bath", "Bed & Bath extravaganza", "2019-01-01"),
                new Coupon("Bed & Bath", "Big Savings for Bed & Bath", "2030-01-01") // future, ignore
        );

        List<Category> categories = List.of(
                new Category("Comforter Sets", "Bedding"),
                new Category("Bedding", "Bed & Bath"),
                new Category("Bed & Bath", null),
                new Category("Soap Dispensers", "Bathroom Accessories"),
                new Category("Bathroom Accessories", "Bed & Bath"),
                new Category("Toy Organizers", "Baby And Kids"),
                new Category("Baby And Kids", null)
        );

        CouponFinder finder = new CouponFinder(coupons, categories);
        System.out.println(finder.findBestCoupon("Bed & Bath"));          // Bed & Bath extravaganza
        System.out.println(finder.findBestCoupon("Bedding"));             // Savings on Bedding OR Best Bedding Bargains
        System.out.println(finder.findBestCoupon("Bathroom Accessories"));// Bed & Bath extravaganza
        System.out.println(finder.findBestCoupon("Comforter Sets"));      // Comforters Sale OR Cozy Comforter Coupon
        System.out.println(finder.findBestCoupon("Toy Organizers"));

    }


}
