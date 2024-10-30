package com.example.demo.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class CategoryDto {

    @Data
    @RequiredArgsConstructor
    public static class CategoryInfo {
        private Long parentCategoryId;     // 부모 카테고리 아이디
        private Long childCategoryId;      // 자식 카테고리 아이디
        private String parentCategoryName; // 부모 카테고리명
        private String childCategoryName;  // 자식 카테고리명
    }

    @Data
    @RequiredArgsConstructor
    public static class ParentCategoryInfo {
        private Long parentCategoryId;                       // 부모 카테고리 아이디
        private String parentCategoryName;                   // 부모 카테고리명
        private String type;                                 // 수입 (income) /지출 (expense)
        private List<ChildCategoryInfo> childCategoryInfoList; // 자식 카테고리 리스트
    }

    @Data
    @RequiredArgsConstructor
    public static class ChildCategoryInfo {
        private Long categoryId;     // 자식 카테고리 아이디
        private String categoryName; // 자식 카테고리명
    }
}