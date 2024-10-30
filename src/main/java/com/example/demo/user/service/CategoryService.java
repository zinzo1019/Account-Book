package com.example.demo.user.service;


import com.example.demo.user.dto.CategoryDto;
import com.example.demo.user.dto.TransactionDto;

import java.util.List;

public interface CategoryService {

    /**
     * 계층형 카테고리 조회
     * 최상위 카테고리를 기준으로 하위 카테고리 리스트를 조회한다.
     * @return
     */
    List<CategoryDto.ParentCategoryInfo> findAllGroupByParentId();

    /**
     * 지출 저장
     * @param transactionDto
     */
    void saveTransaction(TransactionDto transactionDto);
}
