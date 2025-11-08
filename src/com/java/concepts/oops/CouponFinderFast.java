package com.java.concepts.oops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponFinderFast {
    private final Map<String, String> couponMap = new HashMap<>();      // direct coupons
    private final Map<String, String> parentMap = new HashMap<>();      // category -> parent
    private final Map<String, String> resolvedCouponMap = new HashMap<>(); // precomputed category -> final coupon

    public CouponFinderFast(List<Map<String, String>> coupons, List<Map<String, String>> categories) {
        // Build coupon and parent maps
        for (Map<String, String> c : coupons) {
            couponMap.put(c.get("CategoryName"), c.get("CouponName"));
        }

        for (Map<String, String> c : categories) {
            parentMap.put(c.get("CategoryName"), c.get("CategoryParentName"));
        }

        // Precompute final coupon for each category
        for (String category : parentMap.keySet()) {
            resolvedCouponMap.put(category, resolveCoupon(category));
        }
    }

    private String resolveCoupon(String category) {
        // If already computed, return it
        if (resolvedCouponMap.containsKey(category)) {
            return resolvedCouponMap.get(category);
        }

        // If this category has its own coupon
        if (couponMap.containsKey(category)) {
            return couponMap.get(category);
        }

        // Move up the hierarchy to find parent coupon
        String parent = parentMap.get(category);
        if (parent == null) {
            return null;
        }

        String parentCoupon = resolveCoupon(parent);

        // Cache the result for faster future lookup
        resolvedCouponMap.put(category, parentCoupon);
        return parentCoupon;
    }

    public String getCoupon(String category) {
        // O(1) lookup
        return resolvedCouponMap.getOrDefault(category, null);
    }

    // --- Helper methods ---
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

    // --- Demo ---
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

        CouponFinderFast finder = new CouponFinderFast(coupons, categories);

        System.out.println(finder.getCoupon("Comforter Sets"));       // Comforters Sale
        System.out.println(finder.getCoupon("Bedding"));              // Savings on Bedding
        System.out.println(finder.getCoupon("Bathroom Accessories")); // Low price for Bed & Bath
        System.out.println(finder.getCoupon("Soap Dispensers"));      // Low price for Bed & Bath
        System.out.println(finder.getCoupon("Toy Organizers"));       // null
    }
}
