package com.java.concepts.oops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponFinderV1 {

    private final Map<String, String> couponMap = new HashMap<>();
    private final Map<String, String> parentMap = new HashMap<>();

    public CouponFinderV1(List<Map<String, String>> coupons, List<Map<String, String>> categories) {
        // Initialize coupon lookup
        for (Map<String, String> c : coupons) {
            couponMap.put(c.get("CategoryName"), c.get("CouponName"));
        }
        // Initialize parent lookup
        for (Map<String, String> c : categories) {
            parentMap.put(c.get("CategoryName"), c.get("CategoryParentName"));
        }
    }

    public String getCoupon(String category){
        String current = category;

        while (current != null) {
            if (couponMap.containsKey(current)) {
                return couponMap.get(current);
            }
            current = parentMap.get(current);
        }

        return null;
    }

    private static Map<String, String> createCoupon(String category, String coupon) {
        Map<String, String> map = new HashMap<>();
        map.put("CategoryName", category);
        map.put("CouponName", coupon);
        return map;
    }

    private static Map<String, String> createCategory(String category, String parent) {
        Map<String, String> map = new HashMap<>();
        map.put("CategoryName", category);
        map.put("CategoryParentName", parent);
        return map;
    }

    public static void main(String[] args) {
        List<Map<String, String>> coupons = new ArrayList<>();
        coupons.add(createCoupon("Comforter Sets", "Comforters Sale"));
        coupons.add(createCoupon("Bedding", "Savings on Bedding"));
        coupons.add(createCoupon("Bed & Bath", "Low price for Bed & Bath"));

        List<Map<String, String>> categories = new ArrayList<>();
        categories.add(createCategory("Comforter Sets", "Bedding"));
        categories.add(createCategory("Bedding", "Bed & Bath"));
        categories.add(createCategory("Bed & Bath", null));
        categories.add(createCategory("Soap Dispensers", "Bathroom Accessories"));
        categories.add(createCategory("Bathroom Accessories", "Bed & Bath"));
        categories.add(createCategory("Toy Organizers", "Baby And Kids"));
        categories.add(createCategory("Baby And Kids", null));

        CouponFinderV1 finder = new CouponFinderV1(coupons, categories);

        System.out.println(finder.getCoupon("Comforter Sets"));       // Comforters Sale
        System.out.println(finder.getCoupon("Bedding"));              // Savings on Bedding
        System.out.println(finder.getCoupon("Bathroom Accessories")); // Low price for Bed & Bath
        System.out.println(finder.getCoupon("Soap Dispensers"));      // Low price for Bed & Bath
        System.out.println(finder.getCoupon("Toy Organizers"));

    }

}