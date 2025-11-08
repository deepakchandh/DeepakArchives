package com.java.concepts.oops.v2;

public class Category {

    private final String categoryName;
    private final String parentName;

    public Category(String categoryName, String parentName) {
        this.categoryName = categoryName;
        this.parentName = parentName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentName() {
        return parentName;
    }
}
